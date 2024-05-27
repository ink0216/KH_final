package kh.em.albaya.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.em.albaya.board.model.dto.ReviewBoard;
import kh.em.albaya.board.model.service.EditBoardService;
import kh.em.albaya.board.model.service.ReviewBoardService;
import kh.em.albaya.member.model.dto.Member;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("editReviewBoard")
@RequiredArgsConstructor
public class EditBoardController {
	
	private final EditBoardService service;
	private final ReviewBoardService reviewBoardService;
	
	
	// 게시글 작성화면 전환
	@GetMapping("{reviewBoardCode:[12]}/insert")
	public String reviewBoardInsert(
		@PathVariable("reviewBoardCode") int reviewBoardCode) {
		
		return "reviewBoard/reviewBoardWrite";
	}
	
	
	
	/** 게시글 작성
	 * @param reviewBoardCode : 게시판 구분
	 * @param inputBoard : 입력값+ 추가데이터
	 * @param loginMember : 로그인한 회원번호
	 * @param ra
	 * @return
	 */
	@PostMapping("{reviewBoardCode:[12]}/insert")
	public String reviewBoardInsert(
		@PathVariable("reviewBoardCode") int reviewBoardCode,
		@ModelAttribute("inputBoard") ReviewBoard inputBoard,
		@SessionAttribute("loginMember") Member loginMember,
		RedirectAttributes ra
			) {
		
		inputBoard.setReviewBoardCode(reviewBoardCode);
		inputBoard.setMemberNo(loginMember.getMemberNo());
		
		int reviewBoardNo = service.reviewBoardInsert(inputBoard);
		
		
		String message = null;
		String path = null;
		
		if(reviewBoardNo>0) {
			message= "게시글 작성이 완료되었습니다";
			path = "/reviewBoard/"+reviewBoardCode+ "/" + reviewBoardNo;
		} else {
			path = "insert";
			message = "게시글 작성에 실패했습니다";
		}
		ra.addFlashAttribute("message", message);
		
		return "redirect:"+path;
	}
	
	
	// 게시글 삭제
	@PostMapping("{reviewBoardCode:[12]}/{reviewBoardNo:[0-9]+}/delete")
	public String reviewBoardDelete(
		@PathVariable("reviewBoardCode") int reviewBoardCode,
		@PathVariable("reviewBoardNo") int reviewBoardNo,
		@SessionAttribute("loginMember") Member loginMember,
		@RequestParam(value="cp", required=false, defaultValue = "1")int cp,
		RedirectAttributes ra) {
		
		Map<String, Integer> map = new HashMap<>();
		map.put("reviewBoardCode", reviewBoardCode);
		map.put("reviewBoardNo", reviewBoardNo);
		map.put("memberNo", loginMember.getMemberNo());
		
		int result = service.reviewBoardDelete(map);
		
		String path = null;
		String message = null;
		
		if(result>0) {
			path = String.format("/reviewBoard/%d",reviewBoardCode);
			message = "삭제 완료되었습니다";
		}else {
			path = String.format("/reviewBoard/%d/%d/%d", reviewBoardCode, reviewBoardNo,cp);
			message = "삭제 실패하였습니다";
		}
		ra.addFlashAttribute("message",message);
		
		return "redirect:" + path;
	}
	
	
	// 게시글 수정 페이지 전환
	@GetMapping("{reviewBoardCode:[12]}/{reviewBoardNo:[0-9]+}/update")
	public String reviewBoardUpdate(
		@PathVariable("reviewBoardCode")int reviewBoardCode,
		@PathVariable("reviewBoardNo")int reviewBoardNo,
		@SessionAttribute("loginMember") Member loginMember,
		Model model,
		RedirectAttributes ra) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("reviewBoardCode", reviewBoardCode);	
		map.put("reviewBoardNo", reviewBoardNo);
		
		ReviewBoard reviewBoard = reviewBoardService.selectOne(map);
		
		String path = null;
		String message = null;
		
		if(reviewBoard == null) {
			message = "해당 게시글이 존재하지 않습니다";
			path = "reviewBoard/reviewBoardList"; // 게시글 리스트로 보내기
			ra.addFlashAttribute("message",message);
		}else if(reviewBoard.getMemberNo() != loginMember.getMemberNo()){
			message = "본인이 작성한 게시글만 수정할 수 있습니다";
			path = String.format("redirect:/reviewBoard/%d/%d", reviewBoardCode,reviewBoardNo);
			ra.addFlashAttribute("message",message);
		} else {
			path="reviewBoard/reviewBoardUpdate";
			model.addAttribute("reviewBoard",reviewBoard);
		}
		
		return path;
	}
	
	
	// 게시글 수정
	@PostMapping("{reviewBoardCode:[12]}/{reviewBoardNo:[0-9]+}/update")
	public String reviewBoardUpdate(
		@PathVariable("reviewBoardCode")int reviewBoardCode,
		@PathVariable("reviewBoardNo")int reviewBoardNo,
		@ModelAttribute("inputBoard") ReviewBoard inputBoard,
		@SessionAttribute("loginMember") Member loginMember,
		@RequestParam(value="querystring", required = false, defaultValue = "") 
		String querystring,
		RedirectAttributes ra
			) {
		
		inputBoard.setReviewBoardCode(reviewBoardCode);
		inputBoard.setReviewBoardNo(reviewBoardNo);
		inputBoard.setMemberNo(loginMember.getMemberNo());
		
		int result = service.reviewBoardUpdate(inputBoard);
		
		String message = null;
		String path = null;
		
		if(result >0) {
			message = "게시글 수정이 완료되었습니다";
			path = String.format
					("/reviewBoard/%d/%d%s", 
							reviewBoardCode, 
							reviewBoardNo, 
							querystring);
		}else {
			message= "수정 실패하였습니다";
			path = "/reviewBoard/update";
		}
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	

}

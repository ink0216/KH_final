package kh.em.albaya.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.em.albaya.board.model.service.EditBoardService;
import kh.em.albaya.member.model.dto.Member;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("editBoard")
@RequiredArgsConstructor
public class EditBoardController {
	
	private final EditBoardService service;
	
	
	// 게시글 작성화면 전환
	@GetMapping("{reviewBoardCode:[12]}/insert")
	public String reviewBoardInsert(
		@PathVariable("reviewBoardCode") int reviewBoardCode) {
		
		return "reviewBoard/reviewBoardWrite";
	}
	
	
	// 게시글 작성
	@PostMapping("{reviewBoardCode:[12]}/insert")
	public String reviewBoardInsert(
		@PathVariable("reviewBoardCode") int reviewBoardCode,
		@SessionAttribute("loginMember") Member loginMember
			) {
		return null;
	}
	
	
	// 게시글 삭제
	@GetMapping("{reviewBoardCode:[12]}/{reviewBoardNo:[0-9]+}/delete")
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
	
	
	// 게시글 수정
	

}

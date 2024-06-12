package kh.em.albaya.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import kh.em.albaya.board.model.dto.Declare;
import kh.em.albaya.board.model.dto.Pagination;
import kh.em.albaya.board.model.dto.ReviewBoard;
import kh.em.albaya.board.model.service.DeclareService;
import kh.em.albaya.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("declare")
@RequiredArgsConstructor
public class DeclareController {
	
	private final DeclareService service;

	//------------ 게시글 신고 목록 -------------
	
	// 게시글 신고 목록 조회
	@GetMapping("{declareBoardCode:[0-9]+}")
	public String selectDeclareList(
			@PathVariable("declareBoardCode") int declareBoardCode,
			Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
		
		Map<String, Object> map = null; 
		map = service.selectDeclareList(declareBoardCode,cp);
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("declareList", map.get("declareList"));
		
		return "declare/admin"; // 신고 관리 페이지로 이동하기
	}
	
	
	
	/** 비동기 게시글 신고 목록 조회
	 * @return
	 */
	@ResponseBody
	@GetMapping("selectList")
	public Map<String, Object> selectDeclareList(
			@RequestParam ("cp") int cp) {
		
		Map<String, Object> map = null; 
		map = service.selectDeclareList(1,cp);
		
		return map;
	}
	
	
	
	// 게시글 신고 하기
	
	@PostMapping("insert")
	public void insertDeclare(

			@SessionAttribute("loginMember") Member loginMember,
			Declare inputDeclare, 
			Model model, HttpServletResponse resp,
			RedirectAttributes ra) throws IOException {
		
		int memberNo = loginMember.getMemberNo();
		inputDeclare.setMemberNo(memberNo);
		
		int result = service.insertDeclare(inputDeclare);
		
		String message = null;
		
		if(result >0 ) {
			message = "신고 되었습니다";
		}else {
			message = "신고 실패하였습니다";
		}

		ra.addFlashAttribute("message", message);
		resp.getWriter().write("<script>window.close()</script>");
		// 팝업창 닫기
	}
	
	
	
	
	//---------- 신고 처리 ----------------------
	
	// 신고 처리...
//	@GetMapping("")
//	public String updateDeclare(
//			@RequestParam("memberNo")int memberNo,
//			@RequestParam("reportedDeclareNo") int reportedDeclareNo,
//			@SessionAttribute("loginMember") Member loginMember) {
//		return null;
//	}
	
	
	
	// 신고 반려 처리 
	@ResponseBody
	@PutMapping("reject")
	public int rejectDeclare(
			@RequestBody int reviewBoardDeclareNo
			) {
		
		return service.rejectDeclare(reviewBoardDeclareNo);
	}
	
	
	
	// 신고 확정 처리
	@ResponseBody
	@PutMapping("complete")
	public int completeDeclare(
			@RequestBody int reviewBoardDeclareNo,
			RedirectAttributes ra) {
	
		return service.completeDeclare(reviewBoardDeclareNo);
	}
	
	
	
	
}
			
		

	
	
	

	



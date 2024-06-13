package kh.em.albaya.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
import kh.em.albaya.board.model.dto.CommentDeclare;
import kh.em.albaya.board.model.service.CommentDeclareService;
import kh.em.albaya.member.model.dto.Member;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("commentDeclare")
public class CommentDeclareController {
	
	private final CommentDeclareService service;
	
	//------- 댓글 신고 목록 ---------
	
	// 댓글 신고 목록 조회
	@GetMapping("{declareBoardCode:[0-9]+}")
	public String selectCommentDeclareList(
			@PathVariable("declareBoardCode")int declareBoardCode,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			Model model) {

		Map<String, Object> map = null; 
		map = service.selectCommentDeclareList(declareBoardCode,cp);
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("commentDeclareList", map.get("commentDeclareList"));
		
		return "commentDeclare/commentAdmin";
	}
	
	// 비동기 댓글신고 조회
	@ResponseBody
	@GetMapping("selectList")
	public Map<String, Object> selectDeclareList(
			@RequestParam("cp") int cp) {
		
		Map<String, Object> map = null;
		
		map = service.selectCommentDeclareList(1,cp);
		return map;
	}
	
	
	// ----------- 댓글 신고 하기 -----------
	
	// 댓글 신고하기
	@PostMapping("insert")
	public void insertCommentDeclare(
		@SessionAttribute("loginMember") Member loginMember,
		Model model,
		CommentDeclare commentDeclare,
		HttpServletResponse resp,
		RedirectAttributes ra) throws Exception {
		
		int memberNo = loginMember.getMemberNo();
		
		commentDeclare.setMemberNo(memberNo);
		
		int result = service.insertDeclare(commentDeclare);
		
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
	
	
	// ---- 댓글 신고 반려
	
	// 신고 반려 처리 
	@ResponseBody
	@PutMapping("reject")
	public int rejectDeclare(
			@RequestBody int commentDeclareNo
			) {
		
		return service.rejectDeclare(commentDeclareNo);
	}
		
	// 댓글 신고 확정
	@ResponseBody
	@PutMapping("complete")
	public int completeDeclare(
			@RequestBody int commentDeclareNo,
			RedirectAttributes ra) {
	
		return service.completeDeclare(commentDeclareNo);
	}
		
		
		
}

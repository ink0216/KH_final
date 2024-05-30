package kh.em.albaya.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kh.em.albaya.board.model.dto.ReviewBoard;
import kh.em.albaya.board.model.service.DeclareService;
import kh.em.albaya.member.model.dto.Member;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("declare")
@RequiredArgsConstructor
public class DeclareController {
	
	private final DeclareService service;

	// 게시글 신고 목록 조회
	@GetMapping("/declareList")
	public String declareSelect(
			@RequestParam("reviewBoardNo")int reviewBoardNo,
			@SessionAttribute("loginMember")Member loginMember) {
//		service.selectDeclare(reviewBoardNo);
		return null;
	}
	
	
	// 게시글 신고 하기
	@PostMapping()
	public String insertDeclare(
			@RequestParam("boardDeclareContent") String boardDeclareContent,
			@SessionAttribute("loginMember") Member loginMember) {
		
		int memberNo = loginMember.getMemberNo();
		
//		service.insertDeclare(memberNo,boardDeclareContent);
		
		return null;
	}
	
	// 신고 처리...
	@GetMapping()
	public String updateDeclare(
			@RequestParam("memberNo")int memberNo,
			@RequestParam("reportedDeclareNo") int reportedDeclareNo,
			@SessionAttribute("loginMember") Member loginMember) {
		return null;
	}
	
	
	

}

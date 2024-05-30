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

import kh.em.albaya.board.model.dto.Declare;
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
			@SessionAttribute("loginMember")Member loginMember,
			Model model) {
//		service.selectDeclare(reviewBoardNo);
		return null;
	}
	
	
	// 게시글 신고 하기
	@PostMapping("")
	public String insertDeclare(
			@RequestParam("reviewBoardNo")int reviewBoardNo, // 게시글 번호...어디서 얻어옴
			@RequestParam("boardDeclareContent") String boardDeclareContent, //신고 사유
			@SessionAttribute("loginMember") Member loginMember,
			@ModelAttribute Declare inputDeclare,
			Model model) {
		
		int memberNo = loginMember.getMemberNo();
		
		inputDeclare.setBoardDeclareContent(boardDeclareContent);
		inputDeclare.setMemberNo(memberNo);
		inputDeclare.setReviewBoardNo(reviewBoardNo);
		
		
		
		int result = service.insertDeclare(inputDeclare);
		
		String path = null;
		String message = null;
		
		if(result >0 ) {
//			path = "/reviewBoard/reviewBoardList";
			message = "신고 되었습니다";
		}else {
//			path = "/reviewBoard/reviewBoardList";
			message = "신고 실패하였습니다";
		}
		
		return "/reviewBoard/reviewBoardList";
	}
	
	// 신고 처리...
	@GetMapping("")
	public String updateDeclare(
			@RequestParam("memberNo")int memberNo,
//			@RequestParam("reviewBoardNo") int reviewBoardNo,
			@RequestParam("reportedDeclareNo") int reportedDeclareNo,
			@SessionAttribute("loginMember") Member loginMember) {
		return null;
	}
	
	
	

	

}

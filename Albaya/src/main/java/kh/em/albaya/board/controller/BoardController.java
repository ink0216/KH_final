package kh.em.albaya.board.controller;


import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kh.em.albaya.board.model.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("reviewBoard")
@RequiredArgsConstructor
public class BoardController {
   
   private final BoardService service;
   
   
   

	// 게시글 조회
	/**
	 * @param reviewBoardCode : 게시판 종류( 공지, 일반후기)
	 * @param cp : 현재 조회한 요청 페이지
	 * @param model : request scope로 값 전달 객체
	 * @return
	 */
	@GetMapping("{reviewBoardCode:[12]}")
	public String selectBoardList(
		@PathVariable("reviewBoardCode") int reviewBoardCode,
		@RequestParam(value="cp", required=false, defaultValue = "1")int cp,
		Model model
		) {
		// 일반, 비회원, 기업 전부 다 조회 가능
		
		
		Map<String , Object> map = null;
		
		service.selectBoardList(reviewBoardCode,cp);
		
		return null;
	}
	
	
	
	// 게시글 상세 조회 
	@GetMapping("{reviewBoardCode:[12]}/{reviewBoardNo:[0-9]+}")
	public String boardDetail(
			@PathVariable("reviewBoardCode") int reviewBoardCode,
			@PathVariable("reviewBoardNo") int reviewBoardNo,
			Model model) {
		// 일반,비회원,기업 전부 상세 조회 가능
		
		return null;
	}


}
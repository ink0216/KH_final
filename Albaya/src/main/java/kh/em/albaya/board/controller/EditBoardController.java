package kh.em.albaya.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.em.albaya.board.model.service.EditBoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("editBoard")
@RequiredArgsConstructor
public class EditBoardController {
	
	private final EditBoardService service;
	
	
	// 게시글 작성화면 전환
	@GetMapping("{reviewBoardCode:[12]}/insert")
	public String boardInsert(
		@PathVariable("reviewBoardCode") int reviewBoardCode) {
		
		
		return "board/boardWrite";
	}
	
	// 게시글 작성
	
	
	// 게시글 삭제
	

}

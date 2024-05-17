package kh.em.albaya.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditBoardController {
	
	// 게시글 작성화면 전환
	@GetMapping()
	public String boardInsert() {
		
		return "board/boardWrite";
	}
	

}

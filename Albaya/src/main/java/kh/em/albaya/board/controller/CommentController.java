package kh.em.albaya.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.em.albaya.board.model.service.CommentService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService service;
	
	// 댓글 작성

}

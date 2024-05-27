package kh.em.albaya.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kh.em.albaya.board.model.dto.Comment;
import kh.em.albaya.board.model.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService service;
	
	// 댓글 조회
	
	@GetMapping(value="", produces="application/json")
	public List<Comment> select(
			@RequestParam("reviewBoardNo")int reviewBoardNo){
		return service.select(reviewBoardNo);
	}
	
	// 댓글 작성
	@PostMapping("")
	public int insert(
			@RequestBody Comment comment) {
		
		return service.insert(comment);
	}
	
	// 댓글 수정
	@PutMapping("")
	public int update(
			@RequestBody Comment comment) {
		return service.update(comment);
	}
	
	// 댓글 삭제
	@DeleteMapping("")
	public int delete(
			@RequestBody int commentNo) {
		return service.delete(commentNo);
	}

}

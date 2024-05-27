package kh.em.albaya.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kh.em.albaya.board.model.dto.Comment;
import kh.em.albaya.board.model.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor	
public class CommentServiceImpl implements CommentService {
	
	private final CommentMapper mapper;
	
	// 댓글 조회
	@Override
	public List<Comment> select(int reviewBoardNo) {
		return mapper.select(reviewBoardNo);
	}
	
	
	// 댓글 작성
	@Override
	public int insert(Comment comment) {
		
		int result = mapper.insert(comment);
		
		if(result >0) return comment.getCommentNo();
		
		return 0;
	}
	
	// 댓글 수정
	@Override
	public int update(Comment comment) {
		return mapper.update(comment);
	}
	
	// 댓글 삭제
	@Override
	public int delete(int commentNo) {
		return mapper.delete(commentNo);
	}
	
	

}

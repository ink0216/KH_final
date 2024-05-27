package kh.em.albaya.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kh.em.albaya.board.model.dto.Comment;

@Mapper
public interface CommentMapper {

	// 댓글 조회
	List<Comment> select(int reviewBoardNo);

	// 댓글 작성
	int insert(Comment comment);

	// 댓글 수정
	int update(Comment comment);

	// 댓글 삭제
	int delete(int commentNo);

}

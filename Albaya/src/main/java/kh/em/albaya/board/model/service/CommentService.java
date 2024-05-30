package kh.em.albaya.board.model.service;

import java.util.List;

import kh.em.albaya.board.model.dto.Comment;

public interface CommentService {

	
	/** 댓글 조회
	 * @param reviewBoardNo
	 * @return
	 */
	List<Comment> select(int reviewBoardNo);

	/** 댓글 작성
	 * @param comment
	 * @return
	 */
	int insert(Comment comment);

	
	/** 댓글 수정
	 * @param comment
	 * @return
	 */
	int update(Comment comment);

	/** 댓글 삭제
	 * @param commentNo
	 * @return
	 */
	int delete(int commentNo);

}

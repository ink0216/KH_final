package kh.em.albaya.board.model.service;

import java.util.List;
import java.util.Map;

import kh.em.albaya.board.model.dto.CommentDeclare;

public interface CommentDeclareService {
	
	/** 게시판 종류 조회
	 * @return
	 */
	List<Map<String, Object>> selectDeclareTypeList();
	

	
	/** 신고 댓글 목록 조회
	 * @param declareBoardCode
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectCommentDeclareList(int declareBoardCode, int cp);



	/** 댓글 신고하기
	 * @param commentDeclare
	 * @return
	 */
	int insertDeclare(CommentDeclare commentDeclare);



	/** 댓글 신고 반려
	 * @param commentDeclareNo
	 * @return
	 */
	int rejectDeclare(int commentDeclareNo);



	/** 댓글 신고 확정
	 * @param commentDeclareNo
	 * @return
	 */
	int completeDeclare(int commentDeclareNo);

}

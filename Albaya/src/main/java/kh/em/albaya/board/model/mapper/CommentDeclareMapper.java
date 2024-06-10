package kh.em.albaya.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.em.albaya.board.model.dto.CommentDeclare;

@Mapper
public interface CommentDeclareMapper {

	List<Map<String, Object>> selectDeclareTypeList();

	/** 처리중인 신고댓글 수
	 * @param declareBoardCode
	 * @return
	 */
	int getDeclareCount(int declareBoardCode);

	
	/** 신고댓글 목록 출력
	 * @param declareBoardCode
	 * @param rowBounds
	 * @return
	 */
	List<CommentDeclare> selectCommentDeclareList(int declareBoardCode, RowBounds rowBounds);


	/** 중복된 신고
	 * @param commentDeclare
	 * @return
	 */
	int duplicateDeclare(CommentDeclare commentDeclare);


	/** 중복된 신고회원
	 * @param commentDeclare
	 * @return
	 */
	int duplicateMember(CommentDeclare commentDeclare);


	/** 댓글 신고 하기
	 * @param commentDeclare
	 * @return
	 */
	int insertDeclare(CommentDeclare commentDeclare);

	/** 댓글 신고 반려기
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

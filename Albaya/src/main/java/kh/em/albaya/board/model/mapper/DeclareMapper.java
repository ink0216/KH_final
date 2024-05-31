package kh.em.albaya.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.em.albaya.board.model.dto.Declare;
import kh.em.albaya.member.model.dto.Member;

@Mapper
public interface DeclareMapper {

	/**게시글 신고 목록 조회
	 * @param reviewBoardNo
	 * @param loginMember
	 * @return
	 */
	List<Declare> selectDeclare(int reviewBoardNo,RowBounds rowBounds);

	/** 처리중인 신고 목록 수 조회
	 * @param reviewBoardNo
	 * @return
	 */
	int getDeclareListCount(int reviewBoardNo);

	
	/** 게시글 신고
	 * @param memberNo
	 * @param boardDeclareContent
	 * @return
	 */
	int insertDeclare(Declare inputDeclare);

	
}

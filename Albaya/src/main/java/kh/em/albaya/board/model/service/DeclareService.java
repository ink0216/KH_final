package kh.em.albaya.board.model.service;

import java.util.List;
import java.util.Map;

import kh.em.albaya.board.model.dto.Declare;
import kh.em.albaya.member.model.dto.Member;

public interface DeclareService {

	
	/** 게시글 신고 목록 조회
	 * @param reviewBoardNo
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectDeclare(int reviewBoardNo, int cp);
	
	
	/** 게시글 신고 하기
	 * @param memberNo
	 * @param declarePopupContent
	 * @return
	 */
	int insertDeclare(Declare inputDeclare);

	

	


}

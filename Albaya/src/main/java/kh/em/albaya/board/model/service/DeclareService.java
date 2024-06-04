package kh.em.albaya.board.model.service;

import java.util.List;
import java.util.Map;

import kh.em.albaya.board.model.dto.Declare;
import kh.em.albaya.member.model.dto.Member;

public interface DeclareService {


	
	/** 게시글 신고 하기
	 * @param memberNo
	 * @param declarePopupContent
	 * @return
	 */
	int insertDeclare(Declare inputDeclare);




	/** 게시판 종류 조회
	 * @return
	 */
	List<Map<String, Object>> selectDeclareTypeList();


	/**
	 * 신고 게시판의 목록 출력
	 * @param declareBoardCode
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectDeclareList(int declareBoardCode, int cp);



	/**
	 * 중복된 신고
	 * @param reviewBoardCondition
	 * @return
	 */
	int duplicateDeclare(String reviewBoardCondition);




	/** 신고 반려 처리
	 * @param reviewBoardDeclareNo
	 * @return
	 */
	int rejectDeclare(int reviewBoardDeclareNo);




	/** 신고 완료 하기
	 * @param reviewBoardDeclareNo
	 * @param reviewBoardNo
	 * @return
	 */
	int completeDeclare(int reviewBoardDeclareNo, int reviewBoardNo);
	

	

	


}

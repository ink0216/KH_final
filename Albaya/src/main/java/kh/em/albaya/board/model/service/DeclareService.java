package kh.em.albaya.board.model.service;

import java.util.List;
import java.util.Map;

import kh.em.albaya.board.model.dto.Declare;

public interface DeclareService {

	// ------------ 게시글 신고 목록 조회 --------
	
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


	/** 비동기 목록 조회
	 * @return
	 */
	List<Declare> selectDeclareList();
	

	
	// --------------- 게시글 신고 목록 조회 ---------
	
	
	
	
	
	/** 게시글 신고 하기
	 * @param memberNo
	 * @param declarePopupContent
	 * @return
	 */
	int insertDeclare(Declare inputDeclare);






	/**
	 * 중복된 신고
	 * @param reviewBoardCondition
	 * @return
	 */
//	int duplicateDeclare(String reviewBoardCondition);




	/** 신고 반려 처리
	 * @param reviewBoardDeclareNo
	 * @return
	 */
	int rejectDeclare(int reviewBoardDeclareNo);



	/** 신고 확정 처리
	 * @param reviewBoardDeclareNo
	 * @return
	 */
	int completeDeclare(int reviewBoardDeclareNo);


//	// 비동기 중복 검사...
//	int duplicateDeclare(int reviewBoardNo);

	

	


}

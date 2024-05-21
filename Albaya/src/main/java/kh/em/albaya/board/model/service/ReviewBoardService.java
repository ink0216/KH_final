package kh.em.albaya.board.model.service;

import java.util.List;
import java.util.Map;

public interface ReviewBoardService {
	
	/** 게시판 종류 조회
	 * @return
	 */
	List<Map<String, Object>> selectBoardTypeList();
	

	/** 특정 게시판의 지정된 페이지 목록 조회
	 * @param reviewBoardCode
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectBoardTypeList(int reviewBoardCode, int cp);

	/** 검색 서비스
	 * @param paramMap
	 * @param cp
	 * @return
	 */
	Map<String, Object> searchList(Map<String, Object> paramMap, int cp);



}

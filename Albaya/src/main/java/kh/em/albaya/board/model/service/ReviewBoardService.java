package kh.em.albaya.board.model.service;

import java.util.Map;

public interface ReviewBoardService {
	
	

	Map<String, Object> selectBoardTypeList(int reviewBoardCode, int cp);

	Map<String, Object> searchList(Map<String, Object> paramMap, int cp);



}

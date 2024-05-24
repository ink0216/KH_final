package kh.em.albaya.board.model.service;

import java.util.Map;

public interface EditBoardService {

	
	/** 게시글 삭제
	 * @param map
	 * @return
	 */
	int reviewBoardDelete(Map<String, Integer> map);

	
	/** 게시글 수정
	 * @param map
	 * @return
	 */
	int reviewBoardUpdate(Map<String, Integer> map);

}

package kh.em.albaya.board.model.service;

import java.util.Map;

import kh.em.albaya.board.model.dto.ReviewBoard;

public interface EditBoardService {

	/** 게시글 작성
	 * @param inputBoard
	 * @return
	 */
	int reviewBoardInsert(ReviewBoard inputBoard);
	
	/** 게시글 삭제
	 * @param map
	 * @return
	 */
	int reviewBoardDelete(Map<String, Integer> map);


	/**게시글 수정
	 * @param inputBoard
	 * @return
	 */
	int reviewBoardUpdate(ReviewBoard inputBoard)
	
	
	
	
	;
}

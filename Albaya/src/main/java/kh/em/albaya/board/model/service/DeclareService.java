package kh.em.albaya.board.model.service;

import kh.em.albaya.board.model.dto.Declare;

public interface DeclareService {

	
	/** 게시글 신고 하기
	 * @param memberNo
	 * @param declarePopupContent
	 * @return
	 */
	int insertDeclare(Declare inputDeclare);

}

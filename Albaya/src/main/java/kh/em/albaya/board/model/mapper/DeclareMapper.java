package kh.em.albaya.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kh.em.albaya.board.model.dto.Declare;

@Mapper
public interface DeclareMapper {

	/** 게시글 신고
	 * @param memberNo
	 * @param boardDeclareContent
	 * @return
	 */
	int insertDeclare(Declare inputDeclare);

	List<Declare> selectDeclareList(int reviewBoardNo);

}

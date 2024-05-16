package kh.em.albaya.board.model.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

	// 삭제 안된 게시글 수 조회
	int getListCount(int reviewBoardCode);

}

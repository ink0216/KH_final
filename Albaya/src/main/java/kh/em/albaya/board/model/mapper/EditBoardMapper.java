package kh.em.albaya.board.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EditBoardMapper {

	
	// 게시글 삭제
	int reviewBoardDelete(Map<String, Integer> map);

	// 게시글 수정
	int reviewBoardUpdate(Map<String, Integer> map);

}

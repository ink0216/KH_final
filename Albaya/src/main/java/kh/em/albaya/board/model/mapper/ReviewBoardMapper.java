package kh.em.albaya.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.em.albaya.board.model.dto.ReviewBoard;

@Mapper
public interface ReviewBoardMapper {

	// 삭제 안된 게시글 수 조회
	int getListCount(int reviewBoardCode);

	//특정 게시판의 지정된 목록 조회
	List<ReviewBoard> selectBoardList(int reviewBoardCode, RowBounds rowBounds);

	
	// 검색 조건이 맞는 게시글 수 조회
	int getSearchCount(Map<String, Object> paramMap);

}

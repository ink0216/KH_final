package kh.em.albaya.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.em.albaya.board.model.dto.ReviewBoard;

@Mapper
public interface ReviewBoardMapper {

	// 게시판 종류 조회(공지/일반)
	List<Map<String, Object>> selectBoardTypeList();
	
	// 삭제 안된 게시글 수 조회
	int getListCount(int reviewBoardCode);

	//특정 게시판의 지정된 목록 조회
	List<ReviewBoard> selectBoardList(int reviewBoardCode, RowBounds rowBounds);

	
	// 검색 조건이 맞는 게시글 수 조회
	int getSearchCount(Map<String, Object> paramMap);

	// 검색 결과 목록 조회
	List<ReviewBoard> selectSearchList(Map<String, Object> paramMap, RowBounds rowBounds);

	
	
	
	// 게시글 상세조회
	ReviewBoard selectOne(Map<String, Object> map);

	// 조회수 증가
	int updateReadCount(int reviewBoardNo);

	// 현재 조회수 조회
	int selectReadCount(int reviewBoardNo);


}

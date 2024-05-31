package kh.em.albaya.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.em.albaya.board.model.dto.Pagination;
import kh.em.albaya.board.model.dto.ReviewBoard;
import kh.em.albaya.board.model.mapper.ReviewBoardMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewBoardServiceImpl implements ReviewBoardService{

	private final ReviewBoardMapper mapper;
	
	// 게시판 종류 조회
	@Override
	public List<Map<String, Object>> selectBoardTypeList() {
		return mapper.selectBoardTypeList();
	}
	
	// 특정 게시판의 지정된 페이지 목록 조회
	@Override
	public Map<String, Object> selectBoardList(int reviewBoardCode, int cp) {
		
		// 삭제 안된 게시글 조회
		int listCount = mapper.getListCount(reviewBoardCode);
		
		// pagination 객체 생성
		Pagination pagination = new Pagination(cp, listCount);
		
		// 특정게시판 지정된 페이지 목록 조회
		
		int limit = pagination.getLimit();
		int offset = (cp -1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<ReviewBoard> reviewBoardList = mapper.selectBoardList(reviewBoardCode,rowBounds);
		
		// 목록 조회, map으로 묶기
		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("reviewBoardList", reviewBoardList);
		
		return map;
	}
	
	
	
	
	// 검색 서비스
	@Override
	public Map<String, Object> searchList(Map<String, Object> paramMap, int cp) {
		
		int listCount =mapper.getSearchCount(paramMap);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		int limit = pagination.getLimit();
		int offset = (cp-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<ReviewBoard> reviewBoardList = mapper.selectSearchList(paramMap,rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("reviewBoardList", reviewBoardList);
		
		return map;
	}
	
	
	
	// 게시글 상세 조회
	@Override
	public ReviewBoard selectOne(Map<String, Object> map) {
		return mapper.selectOne(map);
	}
	
	
	// 조회수 증가
	@Override
	public int updateReadCount(int reviewBoardNo) {
		
		int result = mapper.updateReadCount(reviewBoardNo);
		
		if(result>0) {
			return mapper.selectReadCount(reviewBoardNo);
		}
		return 0;
	}
	
}
package kh.em.albaya.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import kh.em.albaya.board.model.dto.Pagination;
import kh.em.albaya.board.model.dto.ReviewBoard;
import kh.em.albaya.board.model.mapper.ReviewBoardMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewBoardServiceImpl implements ReviewBoardService{

	private final ReviewBoardMapper mapper;
	
	@Override
	public Map<String, Object> selectBoardTypeList(int reviewBoardCode, int cp) {
		
		// 삭제 안된 게시글 조회
		int listCount = mapper.getListCount(reviewBoardCode);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		int limit = pagination.getLimit();
		int offset = (cp -1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<ReviewBoard> reviewBoardList = mapper.selectBoardList(reviewBoardCode,rowBounds);
		
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
		
		
		
		return null;
	}
	
	
	
	
}

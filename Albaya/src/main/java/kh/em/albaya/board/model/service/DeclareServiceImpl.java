package kh.em.albaya.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import kh.em.albaya.board.model.dto.Declare;
import kh.em.albaya.board.model.dto.Pagination;
import kh.em.albaya.board.model.mapper.DeclareMapper;
import kh.em.albaya.member.model.dto.Member;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeclareServiceImpl implements DeclareService{
	
	private final DeclareMapper mapper;
	
	
	//게시글 신고 목록 조회
	@Override
	public Map<String, Object> selectDeclare(int reviewBoardNo, int cp) {
		
		
		// 처리중인 신고 게시글 수 조회
		int listCount = mapper.getDeclareListCount(reviewBoardNo);
		
		
		Pagination pagination = new Pagination(cp, listCount);
		
		int limit = pagination.getLimit(); 
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit); 
		
		List<Declare> declareList = mapper.selectDeclare(reviewBoardNo,rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("declareList",declareList);
		
		
		return map;
	}
	
	
	
	//게시글 신고하기
	@Override
	public int insertDeclare(Declare inputDeclare) {
		
		
		int result = mapper.insertDeclare(inputDeclare);
		
		return result;
	}
}

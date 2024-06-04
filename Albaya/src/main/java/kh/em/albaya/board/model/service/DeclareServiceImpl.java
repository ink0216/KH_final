package kh.em.albaya.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.em.albaya.board.model.dto.Declare;
import kh.em.albaya.board.model.dto.Pagination;
import kh.em.albaya.board.model.mapper.DeclareMapper;
import kh.em.albaya.member.model.dto.Member;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DeclareServiceImpl implements DeclareService{
	
	private final DeclareMapper mapper;
	
	

	
	
	//게시글 신고하기
	@Override
	public int insertDeclare(Declare inputDeclare) {
		
		
		int result = mapper.insertDeclare(inputDeclare);
		
		return result;
	}
	
	
	
	// 신고 게시판 타입
	@Override
	public List<Map<String, Object>> selectDeclareTypeList() {
		return mapper.selectDeclareTypeList();
	}
	
	
	//신고게시판의 목록 출력
	@Override
	public Map<String, Object> selectDeclareList(int declareBoardCode, int cp) {
		
		int listCount = mapper.getDeclareCount(declareBoardCode);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		int limit = pagination.getLimit();
		int offset = (cp-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Declare> declareList = mapper.selectDeclareList(declareBoardCode, rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("declareList", declareList);
		
		return map;
		
	}
	
	
	// 중복 신고 게시물
	@Override
	public int duplicateDeclare(String reviewBoardCondition) {
		
		int result = mapper.duplicateDeclare(reviewBoardCondition);
		
		return result;
	}
	
	
	
	// 신고 반려 처리
	@Override
	public int rejectDeclare(int reviewBoardDeclareNo) {
		
		return mapper.rejectDeclare(reviewBoardDeclareNo);
	}
	
	// 신고 완료하기
	@Override
	public int completeDeclare(int reviewBoardDeclareNo, int reviewBoardNo) {
		  int result = mapper.completeDeclare(reviewBoardDeclareNo,reviewBoardNo);
		return 0;
	}
	
}

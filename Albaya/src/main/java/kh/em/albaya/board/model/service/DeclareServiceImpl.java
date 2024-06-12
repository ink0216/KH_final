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
	
	// ----- 게시글 신고 목록 조회 -----

	
	// 신고 게시판 타입
	@Override
	public List<Map<String, Object>> selectDeclareTypeList() {
		return mapper.selectDeclareTypeList();
	}
	
	//신고게시판의 목록 출력
	@Override
	public Map<String, Object> selectDeclareList(int declareBoardCode, int cp) {
		
		// 처리중인 신고 게시물 수
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
	
	

	
	
	//게시글 신고하기
	@Override
	public int insertDeclare(Declare inputDeclare) {
		
		// 중복 신고 검사
		
		// 게시물 신고 중복
		int board = mapper.duplicateDeclare(inputDeclare);
		// 회원 신고 중복
		int mem = mapper.duplicateMember(inputDeclare);
		
		int result = 0;
		if(board < 1 && mem <1) {
			result = mapper.insertDeclare(inputDeclare);
		}else {
			result = 0;
		}
		
		return result;
	}
	


	
	
	// 신고 반려 처리
	@Override
	public int rejectDeclare(int reviewBoardDeclareNo) {
		return mapper.rejectDeclare(reviewBoardDeclareNo);
	}
	
	// 신고 확정 처리
	@Override
	public int completeDeclare(int reviewBoardDeclareNo) {
		
		// 신고 확정
		int result = mapper.completeDeclare(reviewBoardDeclareNo);
				
		// 신고 확정된 리뷰pk 번호 얻어와
		// 해당 번호 글 삭제 상태 변경
		int result2 = mapper.updateReviewBoard(reviewBoardDeclareNo);
		
		// 신고횟수 누적
		int result3 = mapper.changeMemberCondition(reviewBoardDeclareNo);
		
		
		if(result>0 && result2>0 && result3>0) {
			return 1;
		}else {
			throw new RuntimeException();
		}
		
	}
	
	

	
}

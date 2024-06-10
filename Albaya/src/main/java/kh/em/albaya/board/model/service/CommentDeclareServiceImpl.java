package kh.em.albaya.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import kh.em.albaya.board.model.dto.CommentDeclare;
import kh.em.albaya.board.model.dto.Declare;
import kh.em.albaya.board.model.dto.Pagination;
import kh.em.albaya.board.model.mapper.CommentDeclareMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentDeclareServiceImpl implements CommentDeclareService {
	
	private final CommentDeclareMapper mapper;
	
	
	@Override
	public List<Map<String, Object>> selectDeclareTypeList() {
		return mapper.selectDeclareTypeList();
	}
	
	// 신고 댓글 목록 조회
	@Override
	public Map<String, Object> selectCommentDeclareList(int declareBoardCode, int cp) {
		// 처리중인 신고 게시물 수
		int listCount = mapper.getDeclareCount(declareBoardCode);
				
		Pagination pagination = new Pagination(cp, listCount);
				
		int limit = pagination.getLimit();
		int offset = (cp-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
				
//		List<Declare> declareList = mapper.selectDeclareList(declareBoardCode, rowBounds);
		List<CommentDeclare> commentDeclareList = mapper.selectCommentDeclareList(declareBoardCode, rowBounds);
				
		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("commentDeclareList", commentDeclareList);
				
				return map;
	}
	
	// 댓글 신고 하기
	@Override
	public int insertDeclare(CommentDeclare commentDeclare) {

		// 중복 신고 검사
		
		// 댓글 신고 중복
		int comment = mapper.duplicateDeclare(commentDeclare);
		// 회원 신고 중복
		int mem = mapper.duplicateMember(commentDeclare);
		
		int result = 0;
		if(comment < 1 && mem <1) {
			result = mapper.insertDeclare(commentDeclare);
		}else {
			result = 0;
		}
		
		return result;
	}

	
	
	// 댓글 신고 반려
	@Override
	public int rejectDeclare(int commentDeclareNo) {
		return mapper.rejectDeclare(commentDeclareNo);
	}
	
	// 댓글 신고 확정
	@Override
	public int completeDeclare(int commentDeclareNo) {
		return mapper.completeDeclare(commentDeclareNo);
	}
	
	
}

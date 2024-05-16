package kh.em.albaya.board.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import kh.em.albaya.board.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardMapper mapper;
	
	@Override
	public Map<String, Object> selectBoardList(int reviewBoardCode, int cp) {
		
		// 삭제 안된 게시글 조회
		int listCount = mapper.getListCount(reviewBoardCode);
		return null;
	}
	
	
	
	
}

package kh.em.albaya.board.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import kh.em.albaya.board.model.dto.ReviewBoard;
import kh.em.albaya.board.model.mapper.EditBoardMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditBoardServiceImpl implements EditBoardService {
	
	private final EditBoardMapper mapper;
	
	
	//게시글 작성
	@Override
	public int reviewBoardInsert(ReviewBoard inputBoard) {
		
		int result = mapper.reviewBoardInsert(inputBoard);
		
		if(result == 0) return 0;
		
		int reviewBoardNo = inputBoard.getReviewBoardNo();
		
		return reviewBoardNo;
	}
	
	// 게시글 삭제
	@Override
	public int reviewBoardDelete(Map<String, Integer> map) {
		return mapper.reviewBoardDelete(map) ;
	}
	
	
	// 게시글 수정
	@Override
	public int reviewBoardUpdate(Map<String, Integer> map) {
		int result = mapper.reviewBoardUpdate(map);
		
		if(result == 0) return 0;
		
		return result;
	}

}

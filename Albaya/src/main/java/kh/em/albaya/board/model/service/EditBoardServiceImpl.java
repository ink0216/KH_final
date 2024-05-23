package kh.em.albaya.board.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import kh.em.albaya.board.model.mapper.EditBoardMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditBoardServiceImpl implements EditBoardService {
	
	private final EditBoardMapper mapper;
	
	
	// 게시글 삭제
	@Override
	public int reviewBoardDelete(Map<String, Integer> map) {
		return mapper.reviewBoardDelete(map) ;
	}

}

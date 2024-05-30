package kh.em.albaya.board.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import kh.em.albaya.board.model.dto.Declare;
import kh.em.albaya.board.model.mapper.DeclareMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeclareServiceImpl implements DeclareService{
	
	private final DeclareMapper mapper;
	
	//게시글 신고하기
	@Override
	public int insertDeclare(Declare inputDeclare) {
		
		
		int result = mapper.insertDeclare(inputDeclare);
		
		return result;
	}

}

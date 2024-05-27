package kh.em.albaya.board.model.service;

import org.springframework.stereotype.Service;

import kh.em.albaya.board.model.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor	
public class CommentServiceImpl implements CommentService {
	
	private final CommentMapper mapper;

}

package kh.em.albaya.main.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.main.model.mapper.MainMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MainServiceImpl implements MainService{
	private final MainMapper mapper;
	
	//공고 전체 리스트 조회
	@Override
	public List<Hire> hireList() {
		return mapper.hireList();
	}
}

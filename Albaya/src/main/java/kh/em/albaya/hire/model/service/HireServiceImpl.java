package kh.em.albaya.hire.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.hire.model.mapper.HireMapper;
import lombok.RequiredArgsConstructor;
@Transactional
@Service
@RequiredArgsConstructor
public class HireServiceImpl implements HireService{
	private final HireMapper mapper;
	
	//공고 등록
	@Override
	public int hireWrite(Hire hire) {
		
		//HIRE테이블에 INSERT하는 매퍼
		int result = mapper.hireInsert(hire);
		if(result ==0) {
			return 0;
		}
		//HIRE테이블에는 INSERT 성공한거다
		
		//HIRE_PERIOD 테이블에 INSERT하기
		
		
		
		
		
		
		int hireNo = hire.getHireNo(); //삽입 성공한 공고 번호
		
		return 0;
	}
}

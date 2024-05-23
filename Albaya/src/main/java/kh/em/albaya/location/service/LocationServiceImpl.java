package kh.em.albaya.location.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.em.albaya.location.dto.Dong;
import kh.em.albaya.location.dto.Dosi;
import kh.em.albaya.location.dto.Sigungu;
import kh.em.albaya.location.mapper.LocationMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional //RuntimeException 발생 시 전체를 다 롤백하는 어노테이션
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{
	private final LocationMapper mapper;
	
	//위치 데이터 모두 db에 insert
	@Override
	public int insertDb(List<Dosi> dosiList, List<Sigungu> sigunguList, List<Dong> dongList) {
		
		
		
		return 0;
	}
	
	
	
}

package kh.em.albaya.location.service;

import java.util.ArrayList;
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
//		// 도/시 삽입
//		for(int i=0; i<dosiList.size(); i++) {
//			Dosi dosi = dosiList.get(i);
//			int result = mapper.insertDosi(dosi);
//			if(result==0) {
//				throw new RuntimeException("도/시 삽입 실패");
//			}
//		}
		
//		// 시/군/구 삽입
//		for(int i=0; i<sigunguList.size(); i++) {
//			Sigungu sigungu = sigunguList.get(i);
//			int result = mapper.insertSigungu(sigungu);
//			if(result==0) {
//				throw new RuntimeException("시/군/구 삽입 실패");
//			}
//		}
		
		// 동 삽입
		for(int i=0; i<dongList.size(); i++) {
			Dong dong = dongList.get(i);
			int result = mapper.insertDong(dong);
			if(result==0) {
				throw new RuntimeException("동 삽입 실패");
			}
		}
		
		
		
		return 1;
	}
	
	
	
}

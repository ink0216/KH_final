package kh.em.albaya.hire.model.service;

import java.util.List;
import java.util.Map;

import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.location.dto.Dong;
import kh.em.albaya.location.dto.Dosi;
import kh.em.albaya.location.dto.Sigungu;

public interface HireService {

	/**공고 INSERT
	 * @param hire
	 * @return
	 */
	int hireWrite(Hire hire);

	/**공고 상세조회
	 * @param hireNo
	 * @return
	 */
	Hire detailHire(int hireNo);

	Map<String,Object> selectHireList(int cp);

	
	/**
	 * 시 도 조회
	 * @return
	 */
	List<Dosi> selectDosi();

	/** 시/도에 따라 시군구 조회하기
	 * @param dosiName
	 * @return
	 */
	List<Sigungu> selectSigungu(String dosiName);

	/**동/읍/면 조회하기
	 * @param sigunguName
	 * @return
	 */
	List<Dong> selectDong(String sigunguName);

	/** 업직종별 조회하기
	 * @return
	 */
	List<Hire> selectKind();

	/**지역별 공고 조회해오기
	 * @param map
	 * @return
	 */
	Map<String,Object> locationHireList(Map<String, Object> map);

	/**업직종별 공고 조회해오기
	 * @param map
	 * @return
	 */
	Map<String, Object> kindHireList(Map<String, Object> map);
	

	
}

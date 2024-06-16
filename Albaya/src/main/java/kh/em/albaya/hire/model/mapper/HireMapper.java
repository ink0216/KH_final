package kh.em.albaya.hire.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.location.dto.Dong;
import kh.em.albaya.location.dto.Dosi;
import kh.em.albaya.location.dto.Sigungu;
import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.resume.model.dto.Resume;
import kh.em.albaya.shop.model.dto.Shop;

/**
 * 
 */
@Mapper
public interface HireMapper {

	/**typeName에 따른 typeNo 조회
	 * @param typeName
	 * @return
	 */
	Integer hireTypeNo(String typeName);

	

	/**INSERT
	 * @param hire
	 * @return
	 */
	int insertWrite(Hire hire);



	/**시군구동이 모두 일치하는 동 번호 조회
	 * @param hireLocation
	 * @return
	 */
	int hireDongNo(Map<String, String> hireLocation);



	/**공고 상세 조회
	 * @param hireNo
	 * @return
	 */
	Hire detailHire(int hireNo);


	int selectListCount();

	/**메인페이지 하단 공고 조회
	 * @param object
	 * @param rowBounds
	 * @return
	 */
	List<Hire> selectHireList(Object object, RowBounds rowBounds);



	
	/**
	 * 시도 조회하기
	 * @return
	 */
	List<Dosi> selectDosi();



	/**시군구 조회하기
	 * @param dosiName
	 * @return
	 */
	List<Sigungu> selectSigungu(String dosiName);



	/**동읍면 조회하기
	 * @param sigunguName
	 * @return
	 */
	List<Dong> selectDong(String sigunguName);



	/** 업직종별 공고 조회하기
	 * @return
	 */
	List<Hire> selectKind();




	/**해당 동 내의 공고 수 얻어오기
	 * @param dongList
	 * @return
	 */
	int dongListCount(List<Integer> dongList);


	/**지역별 공고 조회해오기
	 * @param object
	 * @param rowBounds 
	 * @return
	 */
	List<Hire> locationHireList(List<Integer> dongList1, RowBounds rowBounds);
	
	
	/**해당 시군구 내의 공고 수 얻어오기
	 * @param sigunguNo
	 * @return
	 */
	int sigunguListCount(int sigunguNo);
	
	/**지역별 공고 조회해오기2
	 * @param object
	 * @param rowBounds 
	 * @return
	 */
	List<Hire> locationHireList2(int sigunguNo, RowBounds rowBounds);


	Hire hireInfo(int hireNo);
	/**직종별 공고 수 얻어오기
	 * @param kindList
	 * @return
	 */
	int kindListCount(List<Integer> kindList);



	/**직종별 공고 조회해오기
	 * @param kindList
	 * @param rowBounds
	 * @return
	 */
	List<Hire> kindHireList(List<Integer> kindList, RowBounds rowBounds);



	/** 지원서 작성하기
	 * @param loginMember
	 * @param hire
	 * @return
	 */
	int hireApply(Map<String, Object> map);



	/**공고 수정 시 공고 내용 모두 얻어오기
	 * @param hireNo
	 * @return
	 */
	Hire allHire(int hireNo);

	/** 지원하기 누른 회원 여부 확인
	 * @param hireNo
	 * @param memberNo
	 * @return
	 */
	int hireApplyCheck(Map<String, Object> map);


	/**수정 화면 화면 처음 로딩 시의 시도 시군구 동이름 넣기
	 * @param dongNo
	 * @return
	 */
	Dong initLocation(int dongNo);
	
	
	/**공고 수정하기
	 * @param hire
	 * @return
	 */
	int hireUpdate(Hire hire);



	/**모집 마감일과 현재 비교해서 모집마감/지원하기 보이기
	 * @param hireNo
	 * @return
	 */
	int hireOpen(int hireNo);



	/**저장한 이력서 목록 얻어오기
	 * @param memberNo
	 * @return
	 */
	List<Resume> resumeList(int memberNo);



	/** 지원서 상세보기
	 * @param map
	 * @return
	 */
	Hire hireApplyDetail(Map<String, Object> map);
	
	/**공고 삭제
	 * @param hireNo
	 * @return
	 */
	int hireDelete(int hireNo);




	/** 공고별 지원자
	 * @param hireNo
	 * @return
	 */
	List<Hire> hireList(int hireNo);



	/** 지원자 수
	 * @param hireNo
	 * @return
	 */
	int memberCount(int hireNo);
	/**해당 기업이 작성한 공고 모두 얻어오기
	 * @param shopNo
	 * @return
	 */
	List<Hire> myHireList(int shopNo);



	/** 검색어를 포함한 공고문 개수 조회
	 * @param query
	 * @return
	 */
	int selectListCount2(String query);



	/** 검색어를 포함한 공고문 리스트 조회
	 * @param query
	 * @param rowBounds
	 * @return
	 */
	List<Hire> selectHireList2(String query, RowBounds rowBounds);
	
	
}

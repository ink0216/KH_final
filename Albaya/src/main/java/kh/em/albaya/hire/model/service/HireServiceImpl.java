package kh.em.albaya.hire.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.em.albaya.board.model.dto.Pagination;
import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.hire.model.mapper.HireMapper;
import kh.em.albaya.location.dto.Dong;
import kh.em.albaya.location.dto.Dosi;
import kh.em.albaya.location.dto.Sigungu;
import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.resume.model.dto.Resume;
import kh.em.albaya.shop.model.dto.Shop;
import lombok.RequiredArgsConstructor;
@Transactional
@Service
@RequiredArgsConstructor
public class HireServiceImpl implements HireService{
	private final HireMapper mapper;
	
	//공고 등록
	@Override
	public int hireWrite(Hire hire) {
		/*넘어오는 데이터 :
		 * String )hireTitle, typeName**, hireEnd, hireContent, workDay(요일),
		 * 			workStart, workEnd, dosiName, sigunguName, dongName**, addressDetail
		 * 			,shopName, shopOwner, shopTel, shopEmail 
		 * int ) hireCount, hireTerm, payNo, payInput, hireGender
		 * 
		 * ===============
		 * DB HIRE 테이블 컬럼
		 * HIRE_NO, SHOP_NO, TYPE_NO**, PAY_NO,HIRE_TITLE, HIRE_CONTENT, HIRE_END,
		 *  HIRE_TERM, WORK_DAY, PAY_INPUT, DONG_NO**, WORK_START, WORK_END
		 * NULL) HIRE_START, HIRE_COUNT, HIRE_GENDER, HIRE_STATUS, ADDRESS_DETAIL
		 * */
		
		//typeNo 세팅
		String typeName = hire.getTypeName();
		int typeNo = mapper.hireTypeNo(typeName);
		hire.setTypeNo(typeNo);
		
		String dosiName = hire.getDosiName();
		if(dosiName.equals("경북")) dosiName = "경상북도";
		if(dosiName.equals("경남")) dosiName = "경상남도";
		
		if(dosiName.equals("충북")) dosiName = "충청북도";
		if(dosiName.equals("충남")) dosiName = "충청남도";
		
		if(dosiName.equals("전북특별자치도")) dosiName = "전라북도";
		if(dosiName.equals("전남")) dosiName = "전라남도";
		String sigunguName = hire.getSigunguName();
		String dongName=hire.getDongName();
		
		Map<String, String> hireLocation = new HashMap<>();
		
		hireLocation.put("dosiName", dosiName);
		hireLocation.put("sigunguName", sigunguName);
		hireLocation.put("dongName", dongName);
		
		
		//dongNo 세팅
		int dongNo = mapper.hireDongNo(hireLocation);
		hire.setDongNo(dongNo);
		
		//INSERT
		int result = mapper.insertWrite(hire);
		if(result>0) { //삽입 성공 시
			int hireNo = hire.getHireNo();
			return hireNo;
		}
		
		//삽입 실패 시
		return 0;
	}
	
	//공고 상세 조회
	@Override
	public Hire detailHire(int hireNo) {
		return mapper.detailHire(hireNo);
	}
	
	@Override
	public Map<String, Object> selectHireList(int cp, String query) {
	    int listCount = 0;
	    int offset = 0;

	    // query = 검색어
	    if (query != null) {
	        listCount = mapper.selectListCount2(query);
	        offset = 0;
	    }
	    else {
	        listCount = mapper.selectListCount();
	        offset = 12 + (cp - 1) * 10;
	    }

	    Pagination pagination = new Pagination(cp, listCount, 10, 8, 12);


	    RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

	    List<Hire> hireList = null;
	    if (query != null) {
	        hireList = mapper.selectHireList2(query, rowBounds);
	    }
	    else {
	        hireList = mapper.selectHireList(null, rowBounds);
	    }

	    Map<String, Object> map = new HashMap<>();
	    
	    map.put("hireList", hireList);
	    map.put("pagination", pagination);

	    return map;
	}	 
	

	//시도 조회하기
	@Override
	public List<Dosi> selectDosi() {
		return mapper.selectDosi();
	}
	
	//시군구 조회하기
	@Override
	public List<Sigungu> selectSigungu(String dosiName) {
		return mapper.selectSigungu(dosiName);
	}
	
	
	//동읍면 조회하기
	@Override
	public List<Dong> selectDong(String sigunguName) {
		return mapper.selectDong(sigunguName);
	}
	
	
	
	// 업직종별 공고 조회하기
	@Override
	public List<Hire> selectKind() {
		return mapper.selectKind();
	}
	
	//지역별 공고 조회해오기
	@Override
	public Map<String,Object> locationHireList(Map<String, Object> map) {
		
		List<Integer> dongList =  (List<Integer>)(map.get("dongList"));
		int cp =  (int)(map.get("cp"));
		// 전체 공고 수 조회
				int listCount = mapper.dongListCount(dongList);
				
				Pagination pagination = new Pagination(cp, listCount, 10, 8, 0);
				
				// 13번째 부터 1페이지
				// 1p == 13 ~ 22
				// 2p == 23 ~ 32
				int offset = (cp-1) * 10; 
				RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
				List<Hire> hireList 
				= mapper.locationHireList(dongList, rowBounds);
			
				Map<String, Object> map1
				= Map.of("hireList", hireList, "pagination", pagination);
				
				return map1;
	}
	
	/**********************************************************************************************/
	//지역별 공고 조회해오기2
	@Override
	public Map<String, Object> locationHireList2(Map<String, Object> map) {
		int sigunguNo =  (int)(map.get("sigunguNo"));
		int cp =  (int)(map.get("cp"));
		// 전체 공고 수 조회
				int listCount = mapper.sigunguListCount(sigunguNo);
				
				Pagination pagination = new Pagination(cp, listCount, 10, 8, 0);
				
				// 13번째 부터 1페이지
				// 1p == 13 ~ 22
				// 2p == 23 ~ 32
				int offset = (cp-1) * 10; 
				RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
				List<Hire> hireList 
				= mapper.locationHireList2(sigunguNo, rowBounds);
			
				Map<String, Object> map2
				= Map.of("hireList", hireList, "pagination", pagination);
				
				return map2;
	}
	/**********************************************************************************************/
	
	// 지원하기 페이지 기업 정보 가져오기
	@Override
	public Hire hireInfo(int hireNo) {
		Hire hireInfo = mapper.hireInfo(hireNo);
		if(hireInfo != null) {
			return hireInfo;
		}
		return null;
	}

	//업직종별 공고 조회해오기
	@Override
	public Map<String, Object> kindHireList(Map<String, Object> map) {
		List<Integer> kindList = (List<Integer>)map.get("kindList");
		int cp =  (int)(map.get("cp"));
		int listCount = mapper.kindListCount(kindList);
		
		Pagination pagination = new Pagination(cp, listCount, 10, 8, 0);
		
		int offset = (cp-1) * 10; 
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Hire> hireList
			= mapper.kindHireList(kindList, rowBounds);
		
		Map<String, Object> map1
		= Map.of("hireList", hireList, "pagination", pagination);
		
		return map1;
	}
	
	@Override
	// 지원서 작성하기
	public int hireApply(int memberNo, Hire hire, int hireNo,int resumeNo) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("memberNo", memberNo);
		map.put("hireNo", hireNo);
		map.put("applyTitle", hire.getApplyTitle());
		map.put("applyContent", hire.getApplyContent());
		map.put("resumeNo", resumeNo);
		
		int result = mapper.hireApply(map);
		return result;
	}
	
    //공고 수정 시 공고 내용 모두 조회해오기
	@Override
	public Hire allHire(int hireNo) {
		return mapper.allHire(hireNo);
	}
	
	
	@Override
	public int hireApplyCheck(int hireNo, int memberNo) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("hireNo", hireNo);
		map.put("memberNo", memberNo);
		
		return mapper.hireApplyCheck(map);
	}
	
	// 수정 화면 화면 처음 로딩 시의 시도 시군구 동이름 넣기
	@Override
	public Dong initLocation(int dongNo) {
		return mapper.initLocation(dongNo);
	}
	
	//공고 수정
	@Override
	public int hireUpdate(Hire hire) {
		String typeName = hire.getTypeName();
		int typeNo = mapper.hireTypeNo(typeName);
		hire.setTypeNo(typeNo);
		
		if(hire.getDosiName() !=null) {
			String dosiName = hire.getDosiName();
			if(dosiName.equals("경북")) dosiName = "경상북도";
			if(dosiName.equals("경남")) dosiName = "경상남도";
			
			if(dosiName.equals("충북")) dosiName = "충청북도";
			if(dosiName.equals("충남")) dosiName = "충청남도";
			
			if(dosiName.equals("전북특별자치도")) dosiName = "전라북도";
			if(dosiName.equals("전남")) dosiName = "전라남도";
			String sigunguName = hire.getSigunguName();
			String dongName=hire.getDongName();
			
			Map<String, String> hireLocation = new HashMap<>();
			
			hireLocation.put("dosiName", dosiName);
			hireLocation.put("sigunguName", sigunguName);
			hireLocation.put("dongName", dongName);
			
			
			//dongNo 세팅
			int dongNo = mapper.hireDongNo(hireLocation);
			hire.setDongNo(dongNo);
		}
		
		
		//INSERT
		int result = mapper.hireUpdate(hire);
		if(result>0) { //수정 성공 시
			int hireNo = hire.getHireNo();
			return hireNo;
		}
		
		//수정 실패 시
		return 0;
	}
	// 모집 마감일과 현재 비교해서 모집마감/지원하기 보이기
	@Override
	public int hireOpen(int hireNo) {
		return mapper.hireOpen(hireNo); //마감됐으면 1, 마감 안됐으면 0
	}
	
	//저장한 이력서 목록 얻어오기
	@Override
	public List<Resume> resumeList(int memberNo) {
		return mapper.resumeList(memberNo);
	}
	
	// 지원서 상세보기
	@Override
	public Hire hireApplyDetail(int memberNo, int hireNo) {
		Map <String, Object> map = new HashMap<>();
		
		map.put("memberNo", memberNo);
		map.put("hireNo", hireNo);
		
		Hire hireApplyDetail = mapper.hireApplyDetail(map);

		return hireApplyDetail;
	}
	//공고 삭제
	@Override
	public int hireDelete(int hireNo) {
		return mapper.hireDelete(hireNo);

	}
	
	@Override
	public List<Hire> hireList(int hireNo) {
		return mapper.hireList(hireNo);
	}
	
	@Override
	public int memberCount(int hireNo) {
		return mapper.memberCount(hireNo);
	}
	
	//해당 기업이 작성한 공고 모두 얻어오기
	@Override
	public List<Hire> myHireList(int shopNo) {
		return mapper.myHireList(shopNo);

	}
}

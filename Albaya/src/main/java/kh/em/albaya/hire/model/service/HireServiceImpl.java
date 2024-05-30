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
	public Map<String,Object> selectHireList(int cp) {
		// 전체 공고 수 조회
		int listCount = mapper.selectListCount();
		
		Pagination pagination = new Pagination(cp, listCount, 10, 8, 12);
		
		// 13번째 부터 1페이지
		// 1p == 13 ~ 22
		// 2p == 23 ~ 32
		int offset = 12 + (cp-1) * 10; 
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Hire> hireList = mapper.selectHireList(null, rowBounds);
		
		
		Map<String, Object> map = Map.of("hireList", hireList, "pagination", pagination);
		
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
	public List<String> selectKind() {
		return mapper.selectKind();
	}
}

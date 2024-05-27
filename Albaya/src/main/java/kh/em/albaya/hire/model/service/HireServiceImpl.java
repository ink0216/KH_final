package kh.em.albaya.hire.model.service;

import java.util.HashMap;
import java.util.Map;

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
}

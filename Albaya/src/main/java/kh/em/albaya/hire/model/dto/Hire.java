package kh.em.albaya.hire.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//공고 dto
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hire {
	// AA붙은 것은 공고 작성 html에서 넘어오는 데이터들이다
	// HIRE 테이블 
	private int hireNo; //공고문 번호 
	private int shopNo; //상점 번호
	private int typeNo; //직종 번호AAA
	private String typeName; //직종 이름(여기도 들어오나 확인해보기)
	private int payNo; //시급 / 주급AAAA
	private String payName;
	private String hireTitle; //공고 제목AAA
	private String hireContent; //주요 근무 내용AAAA
	private String hireStart; //모집 시작 날짜AAA
	private String hireEnd; //모집 종료 날짜AAA
	private int hireCount; //모집인원AAA
	private int hireTerm; //근무 기간 하루 / 일주일 ....AAAA
	private String hireTerm2; //근무 기간 하루 / 일주일 ....AAAA
	private String hireGender; //남 여 성별무관AAAAA
	private int applyCount; //지원자 수
	private int hireStatus; //공고 상태 미저장 저장 임시저장
	private String workDay; //근무 요일AAAA
	private int payInput; //급여 숫자AAAA
	private String roadAddress;
	//HIRE_PERIOD 테이블 JOIN(HIRE_NO컬럼으로)
	private String workStart; //근무 시작 시간AAAA
	private String workEnd; //근무 종료 시간AAAA
	
	//SHOP 테이블 조인(SHOP_NO)
	private String shopName; //상점 이름AAAA
	private String address; //주소AAAA
	private String addressDetail; //상세주소AAAA
	private String shopOwner; //담당자명AAAA
	private String shopTel; //담당자 연락처AAAA
	private String shopEmail; //담당자 이메일AAAA
	private String shopProfile; //기업 프로필 이미지
	
	//월급 계산용
	private int hirePay; //급여(월 단위)
	
	// 월화수목금토일 name값 같은 checkbox 사용하면 -> String으로 넘어옴('월,화') 이런 식으로
	//	-> 화면에 뿌리거나 할 때에는 ,를 기준으로 split해서 쪼개면 된다

	//위치정보
	private String dosiName;
	private int dosiNo;
	private String sigunguName;
	private int sigunguNo;
	private String dongName;
	private int dongNo;
	
	// 지원한 공고 내용
	private String resumeNo;
	private int memberNo;
	private String applyTitle;
	private String applyContent;
	
	private String hireDelFl;
}

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
	//HIRE 테이블 컬럼
	private int hireNo; //공고문 번호
	private int shopNo; //상점 번호
	private String typeName; //직종 이름
	private String hireContent; //주요 사업 내용
	private String hireEnd; //모집 마감 날짜
	private int hireTerm; //근무 기간(일 단위)
	private int hirePay; //급여(월 단위)
	private String workDay; //근무 요일
	// 월화수목금토일 name값 같은 checkbox 사용하면 -> String으로 넘어옴('월,화') 이런 식으로
	//	-> 화면에 뿌리거나 할 때에는 ,를 기준으로 split해서 쪼개면 된다
	private int hireCount; //모집인원
	private String hireStart; //모집 시작 날짜
	private String hireGender; //모집 성별
	/*hireNo==
	 * typeName 직종 이름
	 * payNo --급여 종류
	 * payInput
	 * hireTitle
	 * hireContent
	 * hireEnd
	 * hireCount
	 * address
	 * addressDetail
	 * shopName 회사명
	 * hireTerm -- 근무 기간
	 * hireGender==
	 * applyCount==
	 * workStart
	 * workEnd
	 * hireStatus==
	 * workDay
	 * */
}

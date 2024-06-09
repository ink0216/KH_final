package kh.em.albaya.resume.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kh.em.albaya.location.dto.Dong;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
	//RESUME 테이블 컬럼
	private int resumeNo;
	private int memberNo;
	private int career; //1: 신입 / 2: 경력
	private String resumeTitle;
	private String introduce;
	private MultipartFile image;
	private int resumeStatus; // 0:저장 / 1:임시저장
	
	//LICENSE 테이블 컬럼
	private String licenseFrom;
	private String licenseDate; //자격증 취득일
	private String licenseName;
	private int licenseScore;
	
	//RESUME_LOCATION 테이블 컬럼
	List<Dong> dongList;
	
	//CAREER 테이블 컬럼
	private List<Career> careerList;
	
	//RESUME_WORK 테이블 컬럼
	private List<Integer> workTypeList; //희망 직종 리스트
	private int typeNo; //직종 번호
	private String typeName; // 직종명
	
	//RESUME_EDUCATION 테이블 컬럼 졸업, 재학, 휴학, 중퇴, 수료
	private int educationStatusNo; //졸업, 재학, 휴학, 중퇴, 수료
	private String educationStatusName; //졸업, 재학, 휴학, 중퇴, 수료
	private String educationNo; // 초중고대
	private String schoolName; // 학교/기관명
	private String schoolStartDate;
	private String schoolEndDate;
	private String schoolPeriod; // 재학 기간(날짜~날짜)
	//EDUCATION_STATUS랑 EDUCATION은 데이터 넣어놓고, RESUME_EDUCATION테이블이 참조하므로 필요 없음
	
	//EDUCATION 테이블 조인
	private String educationName;
	
	//CAREER 테이블 데이터용
	private String companyName;
	private String startDate;
	private String endDate;
	
}

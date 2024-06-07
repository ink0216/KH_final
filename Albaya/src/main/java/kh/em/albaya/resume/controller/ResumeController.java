package kh.em.albaya.resume.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.resume.model.dto.Resume;
import kh.em.albaya.resume.model.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("resume")
@RequiredArgsConstructor
@Slf4j
public class ResumeController {
	private final ResumeService service;
	/*RESUME테이블
	 * - RESUME_NO, MEMBER_NO, CAREER, RESUME_TITLE, INTRODUCE, IMAGE, RESUME_STATUS
	 * 
	 * LICENSE 자격증
	 * - LICENSE_NO, RESUME_NO, LICENSE_FROM, LICENSE_DATE, LICENSE_NAME, LICENSE_SCORE
	 * 
	 * RESUME_LOCATION - DONG
	 *  - HOPE_NO, RESUME_NO, DONG_NO
	 *  
	 * CAREER
	 * RESUME_WORK (희망직종) - WORK_TYPE
	 * 
	 * RESUME_EDUCATION : 졸업, 재학, 휴학, 중퇴, 수료
	 * - RESUME_EDUCATION_NO, RESUME_NO, EDUCATION_STATUS_NO, EDUCATION_NO, 
	 *   SCHOOL_NAME,SCHOOL_PERIOD
	 *   
	 * EDUCATION : 초, 중,고,대
	 *  - EDUCATION_NO, EDUCATION_NAME
	 *  
	 * EDUCATION_STATUS : 졸업,재학,휴학
	 *  - EDUCATION_STATUS_NO, EDUCATION_STATUS_NAME
	 *
	 * */
	@GetMapping("resumeWrite")
	public String resumeWrite(
			@SessionAttribute("loginMember") Member loginMember,
			Model model
			) {
		String[] addressList = loginMember.getMemberAddress().split("\\^\\^\\^");
		String address = addressList[1];
		model.addAttribute("address", address);
		List<Resume> semiResumeList = service.semiResumeList(loginMember.getMemberNo()); // 임시저장인 이력서 조회해오기
		model.addAttribute("semiResumeList", semiResumeList);
		
		//학력 사항 조회해서 화면 만들기
		List<Resume> educationList = service.educationList();
		model.addAttribute("educationList",educationList);
		return "member/resume";
	} //학력 상태 번호(1:졸업, 2: 재학, 3: 휴학, 4: 중퇴, 5: 수료)
	// 학력 번호(1: 초, 2: 중, 3:고, 4: 2,3년제 대학, 5: 4년제 대학, 6: 대학원
}

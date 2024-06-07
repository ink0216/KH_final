package kh.em.albaya.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("resume")
@RequiredArgsConstructor
@SessionAttributes({"loginShop", "loginMember"})
public class ResumeController {
	/*RESUME테이블
	 * LICENSE 자격증
	 * RESUME_LOCATION - DONG
	 * CAREER
	 * RESUME_WORK (희망직종) - WORK_TYPE
	 * 
	 * RESUME_EDUCATION
	 * EDUCATION
	 * EDUCATION_STATUS
	 * */
	@GetMapping("resumeWrite")
	public String resumeWrite() {
		return "member/resume";
	}
}

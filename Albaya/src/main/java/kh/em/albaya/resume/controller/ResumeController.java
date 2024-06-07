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
	@GetMapping("resumeWrite")
	public String resumeWrite() {
		return "member/resume";
	}
}

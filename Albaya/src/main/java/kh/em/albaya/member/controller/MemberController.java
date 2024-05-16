package kh.em.albaya.member.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService service;
	
	@GetMapping("signup")
	public String signup() {
		return "member/signup";
	}
	
	@PostMapping("signup")
	public String signup(
			Member member,
			RedirectAttributes ra) {
		int result = service.signup(member);
		
		String message = null;
		
		if(result > 0) {
			message = "가입 성공!";
			ra.addFlashAttribute("message", message);
			return "redirect:/";
		}else {
			message = "가입 실패";
			return "redirect:/";
		}
		
	}
}

package kh.em.albaya.myPage.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.myPage.model.service.myPageService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("myPage")
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
public class MyPageController {
	private final myPageService service;
	
	private final BCryptPasswordEncoder bcrypt;
	
	@GetMapping("myPageInfo")
	public String myPageInfo(
	        @SessionAttribute(value = "loginMember", required = false) Member loginMember,
			RedirectAttributes ra) {
		
		String message = null;
		
		if(loginMember == null) {
			message = "로그인 후 이용해주세요.";
			ra.addFlashAttribute("message", message);
			return "redirect:/member/login";
		}
		return "/myPage/myPageInfo";
	}
	
	@GetMapping("myPageCheckPw")
	public String myPageCheckPw() {
		return "/myPage/myPageCheckPw";
	}
	
	@PostMapping("myPageCheckPw")
	public String myPageCheckPw(
			Member member,
	        @SessionAttribute(value = "loginMember", required = false) Member loginMember,
			RedirectAttributes ra) {
		
		int memberNo = loginMember.getMemberNo();
		String memberEmail = member.getMemberEmail();
		String memberPw = member.getMemberPw();
		
		String message = null;
		
		int result = service.myPageCheckPw(memberNo, memberEmail, memberPw);
		
		if(result == 1) {
			ra.addFlashAttribute("message", message);
			return "/myPage/updateMemberInfo";
		}
		else {
			ra.addFlashAttribute("message", message);
			return "redirect:/myPage/myPageInfo";
		}
	}
	
	@GetMapping("updateMemberInfo")
	public String updateMemberInfo() {
		return "updateMemberInfo";
	}
	
	@GetMapping("deleteMember")
	public String deleteMember() {
		return "/myPage/deleteMember";
	}
}

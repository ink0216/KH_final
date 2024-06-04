package kh.em.albaya.myPage.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.myPage.model.service.myPageService;
import kh.em.albaya.shop.model.dto.Shop;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("myPage")
@RequiredArgsConstructor
@SessionAttributes({"loginShop", "loginMember"})
public class MyPageController {
	private final myPageService service;
	
	private final BCryptPasswordEncoder bcrypt;
	
	@GetMapping("myPageInfo")
	public String myPageInfo(
	        @SessionAttribute(value = "loginMember", required = false) Member loginMember,
	        @SessionAttribute(value = "loginShop", required = false) Shop loginShop,
			RedirectAttributes ra) {
		
//		String message = null;
//		
//		if(loginMember == null && loginShop == null) {
//			message = "로그인 후 이용해주세요.";
//			ra.addFlashAttribute("message", message);
//			return "redirect:/member/login";
//		}
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
			return "/main";
		}
		else {
			message = "비밀번호가 일치하지 않습니다.";
			ra.addFlashAttribute("message", message);
			return "redirect:/myPage/myPageCheckPw";
		}
	}
	
	@PostMapping("myPageCheckShopPw")
	public String myPageCheckShopPw(
			Shop shop,
			@SessionAttribute(value = "loginShop", required = false) Shop loginShop,
			RedirectAttributes ra) {
		
		int shopNo = loginShop.getShopNo();
		String shopEmail = shop.getShopEmail();
		String shopPw = shop.getShopPw();
		
		String message = null;
		
		int result = service.myPageCheckShopPw(shopNo, shopEmail, shopPw);
		
		if(result == 1) {
			ra.addFlashAttribute("message", message);
			return "/main";
		}
		else {
			message = "비밀번호가 일치하지 않습니다.";
			ra.addFlashAttribute("message", message);
			return "redirect:/myPage/myPageCheckPw";
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
	
	@PostMapping("deleteMember")
	public String deleteMember(
			Member member,
	        @SessionAttribute(value = "loginMember", required = false) Member loginMember,
			RedirectAttributes ra,
			SessionStatus status) {
		
		int memberNo = loginMember.getMemberNo();
		String memberEmail = member.getMemberEmail();
		String memberPw = member.getMemberPw();
		
		String message = null;
		
		int result = service.myPageCheckPw(memberNo, memberEmail, memberPw);
		
		if(result == 1) {
			int deleteMember = service.deleteMember(memberNo, memberEmail);

			message = "탈퇴되었습니다.";
			ra.addFlashAttribute("message", message);
			status.setComplete();
			return "redirect:/";

		}
		else {
			message = "비밀번호가 일치하지 않습니다.";
			ra.addFlashAttribute("message", message);
			return "redirect:/myPage/deleteMember";
		}
	}
	@PostMapping("deleteShop")
	public String deleteMember(
			Shop shop,
	        @SessionAttribute(value = "loginShop", required = false) Shop loginShop,
			RedirectAttributes ra,
			SessionStatus status) {
		
		int ShopNo = loginShop.getShopNo();
		String ShopEmail = shop.getShopEmail();
		String ShopPw = shop.getShopPw();
		
		String message = null;
		
		int result = service.myPageCheckShopPw(ShopNo, ShopEmail, ShopPw);
		
		if(result == 1) {
			int deleteMember = service.deleteMember(ShopNo, ShopEmail);

			message = "탈퇴되었습니다.";
			ra.addFlashAttribute("message", message);
			status.setComplete();
			return "redirect:/";

		}
		else {
			message = "비밀번호가 일치하지 않습니다.";
			ra.addFlashAttribute("message", message);
			return "redirect:/myPage/deleteMember";
		}
	}
	@GetMapping("changePw")
	public String changePw() {
		return "/myPage/changePw";
	}
	
	@PostMapping("changePw")
	public String changePw(
			@SessionAttribute(value = "loginMember", required = false) Member loginMember,
			@RequestParam("curPassword") String curPassword,
			@RequestParam("newPassword") String newPassword,
			RedirectAttributes ra) {
		int memberNo = loginMember.getMemberNo();
		
		int result = service.findMemberPw(memberNo, curPassword, newPassword);
		
		String message = null;
		if(result >= 1) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			loginMember.setLastModifiedPwDate(sdf.format(new Date()));
			
			message = "비밀번호가 변경되었습니다";
			ra.addFlashAttribute("message", message);
			return "redirect:/myPage/myPageInfo";
		}
		else {
			message = "비밀번호가 일치하지 않습니다.";
			ra.addFlashAttribute("message", message);
			return "redirect:/myPage/changePw";
		}
	}
	
	@PostMapping("changePwShop")
	public String changePwShop(
			@SessionAttribute(value = "loginShop", required = false) Shop loginShop,
			@RequestParam("curPassword") String curPassword,
			@RequestParam("newPassword") String newPassword,
			RedirectAttributes ra) {
		int shopNo = loginShop.getShopNo();
		
		int result = service.findShopPw(shopNo, curPassword, newPassword);
		
		String message = null;
		if(result >= 1) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			loginShop.setLastModifiedPwDate(sdf.format(new Date()));
			
			message = "비밀번호가 변경되었습니다";
			ra.addFlashAttribute("message", message);
			return "redirect:/myPage/myPageInfo";
		}
		else {
			message = "비밀번호가 일치하지 않습니다.";
			ra.addFlashAttribute("message", message);
			return "redirect:/myPage/changePw";
		}
	}
	
    @PostMapping("changeProfile")
	public String changeProfile(
			Shop shop,
			RedirectAttributes ra,
			@RequestParam("profileImg") MultipartFile profileImg,
			@SessionAttribute("loginShop") Shop loginShop) throws IllegalStateException, IOException {
    	int result = service.changeProfile(shop, profileImg);
    	
    	String message = null; 		
    	
    	if(result == 1) {
    		message = "변경되었습니다.";
    		 		
    		String newImg = shop.getShopProfile();
    		loginShop.setShopProfile(newImg);
  
    		ra.addFlashAttribute("message", message);
    		return "redirect:/myPage/myPageInfo";
    	}
    	else {
    		message = "변경 실패.;;;";
    		ra.addFlashAttribute("message", message);
    		return "redirect:/myPage/myPageInfo";
    	}
	}
}

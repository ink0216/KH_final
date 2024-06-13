package kh.em.albaya.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.main.model.service.MainService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final MainService service;
	
	
	@RequestMapping("")
	public String mainPage(Model model) {
	    List<Hire> hireList = service.hireList();

	    // box 모양으로 나올 최대 12개의 공고
	    List<Hire> boxList = new ArrayList<>();
	    for(int i = 0; i < Math.min(12, hireList.size()); i++) {
	        Hire hire = hireList.get(i);
	        boxList.add(hire);
	    }

	    model.addAttribute("boxList", boxList);
	    return "/main";
	}
   


   @GetMapping("loginError")
	public String loginError(RedirectAttributes ra) {
		ra.addFlashAttribute("message", "기업 회원으로 로그인 후 이용해 주세요");
		return "redirect:/member/login"; 
	}
   
   @GetMapping("loginError2")
   public String loginError2(RedirectAttributes ra) {
		ra.addFlashAttribute("message", "로그인 후 이용해 주세요");
		return "redirect:/member/login";
   }
   @GetMapping("loginError3")
   public String loginError3(RedirectAttributes ra) {
	   ra.addFlashAttribute("message", "개인회원으로 로그인 후 이용해 주세요");
	   return "redirect:/member/login";
   }
   
//   @GetMapping("loginError3")
//   public String loginError3(RedirectAttributes ra) {
//		ra.addFlashAttribute("message", "관리자 전용 페이지입니다");
//		return "redirect:/";
//   }

   
   @GetMapping("/declarePopup")
	public String declarePopup() {
		return "/declarePopup";
	}
   
   
   @GetMapping("/commentDeclarePopup")
	public String commentDeclarePopup() {
		return "/commentDeclarePopup";
	}	
	
   
   // 댓글 관리 페이지 임시용
   @GetMapping("/declare/commentAdmin")
   public String adminPage() {
	   return "/declare/commentAdmin";
   }
   
   
}
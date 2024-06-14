package kh.em.albaya.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.member.model.service.MemberService;
import kh.em.albaya.sms.config.SMSConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
@PropertySource("classpath:/config.properties")
@SessionAttributes({"loginShop", "loginMember"})
@Slf4j
public class MemberController {
   
   private final MemberService service;
   
   final DefaultMessageService messageService;
    
   private final SMSConfig smsConfig;


   @GetMapping("signup")
   public String signup() {
      return "/member/signup";
   }
   
   @GetMapping("selectSignup")
   public String selectSignup() {
	   return "/member/selectSignup";
   }
   
   @PostMapping("signup")
   public String signup(
         Member member,
         @RequestParam("memberAddress") String[] memberAddress,
         RedirectAttributes ra) {
      int result = service.signup(member, memberAddress);
      
      String message = null;
      
      if(result > 0) {
         message = "가입 성공!";
         ra.addFlashAttribute("message", message);
         return "redirect:/";
      }
      else {
         message = "가입 실패";
         return "redirect:/";
      }
   }

    @PostMapping("send-one")
    @ResponseBody
    public SingleMessageSentResponse sendOne(@RequestBody Map<String, String> map) {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01077568620");
        message.setTo(map.get("inputTel"));
        
        String msg = "[Albaya] 회원가입 인증 번호[" + map.get("msg") + "]를 입력해주세요.";
        message.setText(msg);

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }
    
    @GetMapping("login")
    public String login() {
    	return "/member/login";
    }
    
    @PostMapping("login")
    public String login(
    		Member inputMember,
    		Model model,
			@RequestParam(value = "saveId", required = false) String saveId,
			HttpServletResponse resp,	
    		RedirectAttributes ra,
    		HttpSession session,
    		@SessionAttribute(value = "uri", required = false) String uri) {
    	
		Member loginMember = service.login(inputMember);
    	
//		String uri = (String) session.getAttribute("uri");

		String message = null;
		
		
		if(loginMember == null) {
			
			
			message = "아이디 또는 비밀번호가 일치하지 않습니다";
			ra.addFlashAttribute("message", message);
			ra.addFlashAttribute("message", message);
			return "redirect:/member/login";
		}
		
		
		if(loginMember != null) {
			
			
			
			
			model.addAttribute("loginMember", loginMember);
			
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
	
			cookie.setPath("/");
			
			if(saveId != null) {
				cookie.setMaxAge(30 * 24 * 60 * 60);
			}
			else {
				cookie.setMaxAge(0);
			}
			resp.addCookie(cookie);
			
			if(uri != null) {
				return "redirect:" + uri;
			}
		}
		return "redirect:/";
    }
    
    
    @GetMapping("logout")
    public String logout(
    		SessionStatus status,
    		HttpSession session
    		) {
    	status.setComplete();
    	session.invalidate();
    	return "redirect:/";
    }
    
    @GetMapping("findId")
    public String findId() {
    	return "/member/findId";
    }
    
    @PostMapping("findId")
    @ResponseBody
    public int findId(
    		@RequestBody Member member,
    		Model model,
    		HttpSession session) {
    	int result = service.findId(member);
    	
    	String memberEmail = service.findMemberId(member);
    	session.setAttribute("result", result);
    	session.setAttribute("memberEmail", memberEmail);
    
    	return result;
    }
    
    @PostMapping("findIdResult")
    public String findIdResult(HttpSession session) {
    	return "/member/findIdResult";
    }      
    
    @GetMapping("findPw")
    public String findPw() {
    	return "/member/findPw";
    }
    
    @PostMapping("findPw")
    @ResponseBody
    public int findPw(
    		@RequestBody Member member,
    		Model model) {
    	int memberCount = service.memberPwCount(member);
   
    	String memberEmail = member.getMemberEmail();
    	
    	model.addAttribute("memberEmail", memberEmail);
    	
    	if(memberCount > 0) {
    		return memberCount;
    	}
    	return 0;
    }
    
    @GetMapping("findPwResult")
    public String changePw() {
    	return "/member/findPwResult";
    }
    @GetMapping("resume")
    public String getResume () {
    	return "/member/resume";
    }
    
    @PostMapping("findPwResult")
    public String findPwResult() {
    	return "/member/findPwResult";
    }
    
    @PostMapping("chagePw")
    public String chagePw(
    		@RequestParam("newPassword") String newPassword,
    		@RequestParam("checkNewPassword") String checkNewPassword,
    		@RequestParam("memberEmail") String memberEmail, 
    		RedirectAttributes ra,
    		HttpSession session
    		) {
    	
    	int result = service.changePw(newPassword, memberEmail);
    	
    	if (result == 1) {
    		return "redirect:/";
    	}
    	
    	return "redirect:/";
    }
    
    
    
    @GetMapping("/search")
    @ResponseBody
    public Member getMemberByNo(@RequestParam("memberNo") int memberNo) {
        return service.findMemberByNo(memberNo);
    }
    
    @PostMapping("checkEmailRedundancy")
    @ResponseBody
    public int checkEmail(@RequestBody String memberEmail) {
    	return service.checkEmail(memberEmail);
    }
    
    @PostMapping("checkTelRedundancy")
    @ResponseBody
    public int checkTel(@RequestBody String memberPhoneNumber) {
    	return service.checkTel(memberPhoneNumber);
    }
    

    @PostMapping("checkPwRedundancy")
    @ResponseBody
    public int checkPw(@RequestBody Member member) {
    	String memberEmail =  member.getMemberEmail();
    	String memberPw = member.getMemberPw();
    	
    	return service.checkPw(memberEmail, memberPw);
    }
    
    

    
}
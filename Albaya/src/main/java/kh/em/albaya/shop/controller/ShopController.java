package kh.em.albaya.shop.controller;

import java.util.Map;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kh.em.albaya.member.controller.MemberController;
import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.shop.model.dto.Shop;
import kh.em.albaya.shop.model.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
@RequiredArgsConstructor
@RequestMapping("shop")
@SessionAttributes({"loginShop"})
@Slf4j
@PropertySource("classpath:/config.properties")
public class ShopController {
	private final ShopService service;
			
	final DefaultMessageService messageService;
	
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
    
    @GetMapping("signup")
    public String signup() {
    	return "/shop/signup";
    }
    
    @PostMapping("checkEmail")
    @ResponseBody
    public int checkEmail(@RequestBody Shop shop) {
    	String shopEmail = shop.getShopEmail();
    	int result = service.checkEmail(shopEmail);
        return result;
    }
    
    @PostMapping("checkBrn")
    @ResponseBody
    public int checkBrn(@RequestBody Shop shop) {
    	String shopBrn = shop.getShopBrn();
    	int result = service.checkBrn(shopBrn);
    	return result;
    }
    
    @PostMapping("signup")
    public String signup(
    		Shop shop,
    		RedirectAttributes ra) {
    	int result = service.signup(shop);
    	
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
    
    @PostMapping("login")
    public String login(
    		Shop inputShop,
    		Model model,
			@RequestParam(value = "saveId", required = false) String saveId,
			HttpServletResponse resp,	
    		RedirectAttributes ra) {
    	
		Shop loginShop = service.login(inputShop);
    	
		String message = null;

		
		if(loginShop == null) {
			message = "아이디 또는 비밀번호가 일치하지 않습니다";
			ra.addFlashAttribute("message", message);
			return "redirect:/member/login";
		}

		if(loginShop != null) {
			model.addAttribute("loginShop", loginShop);
			
			Cookie cookie = new Cookie("saveId", loginShop.getShopEmail());
	
			cookie.setPath("/");
			
			if(saveId != null) {
				cookie.setMaxAge(30 * 24 * 60 * 60);
			}
			else {
				cookie.setMaxAge(0);
			}
			resp.addCookie(cookie);
			
		}
		return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(
    		SessionStatus status
    		) {
    	status.setComplete();
    	return "redirect:/";
    }
}

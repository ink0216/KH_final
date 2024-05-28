package kh.em.albaya.shop.controller;

import java.util.Map;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
@SessionAttributes({"loginMember"})
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
}

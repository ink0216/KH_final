package kh.em.albaya.shop.controller;

import java.util.Map;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import kh.em.albaya.member.controller.MemberController;
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
    public int postMethodName(@RequestBody String memberEmail) {  
        return service.checkEmail(memberEmail);
    }
    
}

package kh.em.albaya.resume.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import kh.em.albaya.myPage.model.service.myPageService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("resume")
@RequiredArgsConstructor
@SessionAttributes({"loginShop", "loginMember"})
public class ResumeController {

}

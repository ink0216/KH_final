package kh.em.albaya.hire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hire")
public class HireController {
   @GetMapping("hireWrite")
   public String hireWrite() {
      return "/hire/hireWrite";
   }
}
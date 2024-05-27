package kh.em.albaya.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.main.model.service.MainService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final MainService service;
   @RequestMapping("")
   public String mainPage(
		   Model model
		   ) {
	  List<Hire> hireList = service.hireList();
	  
	  //box 모양으로 나올 12개의 공고
	  List<Hire> boxList = new ArrayList<>();
	  for(int i=0; i<12; i++) {
		  Hire hire = hireList.get(i);
		  boxList.add(hire);
	  }
	  
	
	  
	  model.addAttribute("boxList", boxList);
      return "/main";
   }
   

   


   
}
package kh.em.albaya.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
   @RequestMapping("")
   public String mainPage() {
      return "/main";
   }
   
   // 알바 후기 게시판으로 접속(테스트를 위한 임시 작성)
   @RequestMapping("reviewBoardList")
   @GetMapping("reviewBoardList") 
   public String reviewBoard() {
	   
	   return "reviewBoard/reviewBoardList";
   }
   
   // 알바 게시판 상세 조회(테스트를 위한 임시 작성)
   @RequestMapping("reviewBoardDetail")
   @GetMapping("reviewBoardList")
   public String reviewBoardList() {
	   
	   return "reviewBoard/reviewBoardDetail";
   }
   
  
  
	   
   
}
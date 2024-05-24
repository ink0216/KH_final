package kh.em.albaya.board.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.em.albaya.board.model.dto.ReviewBoard;
import kh.em.albaya.board.model.service.ReviewBoardService;
import kh.em.albaya.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("reviewBoard")
@RequiredArgsConstructor
public class ReviewBoardController {
   
   private final ReviewBoardService service;
   

   // 게시글 조회
   /**
    * @param reviewBoardCode : 게시판 종류( 공지, 일반후기)
    * @param cp : 현재 조회한 요청 페이지
    * @param model : request scope로 값 전달 객체
    * @return
    */
      
   
//   @GetMapping("{reviewBoardCode:[0-9]{2}}")
   @GetMapping("{reviewBoardCode:[0-9]+}")
   public String selectBoardList(
      @PathVariable("reviewBoardCode") int reviewBoardCode, // 공지, 일반게시판 코드
      @RequestParam(value="cp", required=false, defaultValue = "1") int cp,// 현재페이지
      Model model,
      @RequestParam Map<String, Object> paramMap
      ) {
      // 일반, 비회원, 기업 전부 다 조회 가능


		
		Map<String , Object> map = null;
		
		//검색 아닐때
		if(paramMap.get("key")==null) {
			map = service.selectBoardList(reviewBoardCode, cp); // 게시글 목록 조회
		}else { //검색일때
			
			paramMap.put("reviewBoardCode", reviewBoardCode); 
			
			map = service.searchList(paramMap,cp); // 검색 서비스
		}
		
		model.addAttribute("pagination",map.get("pagination"));
		model.addAttribute("reviewBoardList",map.get("reviewBoardList"));
		
		return "reviewBoard/reviewBoardList";
	}
	
	
	
	// 게시글 상세 조회 
	@GetMapping("{reviewBoardCode:[0-9]+}/{reviewBoardNo:[0-9]+}")
	public String boardDetail(
			@PathVariable("reviewBoardCode") int reviewBoardCode,
			@PathVariable("reviewBoardNo") int reviewBoardNo,
//			@SessionAttribute("loginMember") Member loginMember,
			Model model,
			RedirectAttributes ra
			) {
		// 일반,비회원,기업 전부 상세 조회 가능
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("reviewBoardCode", reviewBoardCode);
		map.put("reviewBoardNo", reviewBoardNo);
		
		ReviewBoard reviewBoard = service.selectOne(map);
		
		String path = null;
		
		// 조회 결과 없는 경우
		if(reviewBoard == null) {
			path = "redirect:/reviewBoard/" + reviewBoardCode;
			ra.addFlashAttribute("message", "게시글이 존재하지 않습니다");
		}else { // 있는 경우
			path = "reviewBoard/reviewBoardDetail";
			model.addAttribute("reviewBoard",reviewBoard);
			// 조회수 관련 코드 추가예정
		}
		
		
		return path;
	}



}
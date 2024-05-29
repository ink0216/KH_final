package kh.em.albaya.board.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
			Model model,
			RedirectAttributes ra,
			@SessionAttribute(value = "loginMember", required = false) Member loginMember,
			HttpServletRequest req, // 요청에 담긴 쿠키 얻어오기
			HttpServletResponse resp // 새로운 쿠키 만들어 응답
			) throws ParseException {
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

			/** --- 쿠키 이용 조회수 증가 --- **/
			
			// 로그인 아닌 경우
			if(loginMember == null || 
			   loginMember.getMemberNo() != reviewBoard.getMemberNo()) {
				
				// 요청에 담겨있는 모든 쿠키 얻어오기
				Cookie[] cookies = req.getCookies();
				
				Cookie c = null;
				for(Cookie temp : cookies) {
					
					if(temp.getName().equals("readReviewBoardNo")) {
						c = temp;
						break;
					}
					
				}
				
				int result = 0;
				
				if(c == null) {
					
					c = new Cookie("readReviewBoardNo", "[" + reviewBoardNo + "]");
					result = service.updateReadCount(reviewBoardNo);
				}
				else {
					
					if(c.getValue().indexOf("["+reviewBoardNo+"]") == -1) {
						
						c.setValue(c.getValue()+"["+reviewBoardNo+"]");
						result = service.updateReadCount(reviewBoardNo);
					}
				}
				
				if(result >0) {
					
					reviewBoard.setReadCount(result);

					// 적용 경로 설정
					c.setPath("/"); // "/" 이하 경로 요청 시 쿠키 서버로 전달

					// 수명 지정
					Calendar cal = Calendar.getInstance(); // 싱글톤 패턴
					cal.add(cal.DATE, 1);

					// 날짜 표기법 변경 객체 (DB의 TO_CHAR()와 비슷)
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					// java.util.Date
					Date a = new Date(); // 현재 시간

					Date temp = new Date(cal.getTimeInMillis()); // 다음날 (24시간 후)

					Date b = sdf.parse(sdf.format(temp)); // 다음날 0시 0분 0초

					// 다음날 0시 0분 0초 - 현재 시간
					long diff = (b.getTime() - a.getTime()) / 1000;
					// -> 다음날 0시 0분 0초까지 남은 시간을 초단위로 반환

					log.debug("diff {}", diff);
					
					c.setMaxAge((int) diff); // 수명 설정

					resp.addCookie(c); // 응답 객체를 이용해서 클라이언트에게 전달
					
				}
			}
				
			/** --- 조회수 증가 끝 --- **/
			
			
			
			path = "reviewBoard/reviewBoardDetail";
			model.addAttribute("reviewBoard",reviewBoard);
			// 조회수 관련 코드 추가예정
		}
		
		
		return path;
	}



}
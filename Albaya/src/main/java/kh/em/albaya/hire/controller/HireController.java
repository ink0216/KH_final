package kh.em.albaya.hire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.hire.model.service.HireService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("hire")
@RequiredArgsConstructor
public class HireController {
	private final HireService service;
	
	/**공고 등록 페이지로 이동
	 * @return
	 */
	@GetMapping("hireWrite")
	public String hireWrite() {
		return "/hire/hireWrite";
	}
	/**진짜로 공고 등록(INSERT)
	 * @param hire
	 * @return
	 */
	@PostMapping("hireWrite")
	public String hireWrite(
			Hire hire,
			Model model
			) {
		/*hireNo
		 * typeNo --직종 번호
		 * payNo --급여 종류
		 * hireTitle
		 * hireContent
		 * hireStart
		 * hireEnd
		 * hireCount
		 * hireTerm -- 근무 기간
		 * hireGender
		 * applyCount
		 * hireStatus
		 * 
		 * --아직 없는 네 개!!
		 * hireGender
		 * */
		return "redirect:/"; 
		//일단 메인페이지로 리다이렉트(나중에 작성된 공고 상세조회 페이지로 변경예정)
	}
	
	@GetMapping("hirePopup")
	public String hirePopup() { //업직종 선택 팝업창으로 이동
		return "/hire/hirePopup";
	}
}


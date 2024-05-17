package kh.em.albaya.hire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.em.albaya.hire.model.dto.Hire;

@Controller
@RequestMapping("hire")
public class HireController {

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
			Hire hire
			) {
		return "redirect:/"; 
		//일단 메인페이지로 리다이렉트(나중에 작성된 공고 상세조회 페이지로 변경예정)
	}
}


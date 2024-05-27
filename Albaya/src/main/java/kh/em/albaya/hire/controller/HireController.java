package kh.em.albaya.hire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.hire.model.service.HireService;
import kh.em.albaya.member.model.dto.Member;
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
			Model model,
			@SessionAttribute("loginMember") Member loginMember,
			RedirectAttributes ra
			) { //SEQUENCE : 공고 번호 시퀀스만 생성했다
		//HIRE 테이블에 INSERT하는 서비스 호출
		int shopNo = loginMember.getMemberNo();
		hire.setShopNo(shopNo);
		
		int hireNo = service.hireWrite(hire);
//		hire.setShopNo(loginMember.get)
		String message=null;
		String path = null;
		/*넘어오는 데이터 :
		 * String )hireTitle, typeName, hireEnd, hireContent(근무기간), workDay(요일),
		 * 			workStart(별칭), workEnd(별칭), dosiName, sigunguName, dongName, addressDetail
		 * 			,shopName, shopOwner, shopTel, shopEmail 
		 * int ) hireCount, hireTerm, payNo, payInput, hireGender
		 * */
		
		if(hireNo>0) {
			//공고 등록 성공 시
			message="공고가 성공적으로 등록되었습니다.";
			path="/"; //일단 메인페이지
			//path="/hire/"+hireNo; //등록 성공한 공고 상세조회 페이지로 
		}else {
			//공고 등록 실패 시
			message="공고 등록에 실패하였습니다.";
			path = "hireWrite";
		}
		ra.addFlashAttribute("message", message);
		return "redirect:"+path; 
	}
	
	@GetMapping("hirePopup")
	public String hirePopup() { //업직종 선택 팝업창으로 이동
		return "/hire/hirePopup";
	}
	//@GetMapping("{boardCode:[0-9]+}")
	/**공고 상세 조회 페이지로 이동
	 * @param hireNo
	 * @return
	 */
	@GetMapping("{hireNo:[0-9]+}")
	public String hireDetail(
			@PathVariable("hireNo") int hireNo,
			Model model
			) {
		Hire hire = service.detailHire(hireNo);
		model.addAttribute("hire", hire);
		return "/hire/hireDetail";
	}
	
	
	
	
	
	
	
	//공고문 상세조회 하기
	@GetMapping("hireDetail")
	public String hireDetail() {
		return "/hire/hireDetail";
	}
}


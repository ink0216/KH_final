package kh.em.albaya.hire.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.hire.model.service.HireService;
import kh.em.albaya.shop.model.dto.Shop;
import kh.em.albaya.location.dto.Dong;
import kh.em.albaya.location.dto.Dosi;
import kh.em.albaya.location.dto.Sigungu;
import kh.em.albaya.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
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
			@SessionAttribute("loginShop") Shop loginShop,
			RedirectAttributes ra
			) { //SEQUENCE : 공고 번호 시퀀스만 생성했다
		//HIRE 테이블에 INSERT하는 서비스 호출
		int shopNo = loginShop.getShopNo();
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
			path="/hire/"+hireNo; //등록 성공한 공고 상세조회 페이지로 
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
			@RequestParam(value="cp",required = false, defaultValue="1") int cp, 
			Model model
			) {
		Hire hire = service.detailHire(hireNo);
		String workDay1 = hire.getWorkDay(); //다 합쳐져있는 버전
		
		String[] workDayList = workDay1.split(",");
		String workDay = null;
		log.debug("workDay : "+workDay);
		hire.setWorkDay(workDay);
		model.addAttribute("workDayList", workDayList);
		model.addAttribute("hire", hire);
		
		return "/hire/hireDetail";
	}
	
	
	
	/**메인페이지 하단 공고 조회
	 * @param cp
	 * @return
	 */
	@GetMapping("selectHireList")
	@ResponseBody
	public Map<String,Object> selectHireList(@RequestParam("cp") int cp){
		return service.selectHireList(cp);
	}
	
	/*const index3
	 * = sigunguList.indexOf(
	 * e.target.innerHTML);
     
     sigunguList.splice(index3,1);
	 * */
	
	
	//지역별 공고 조회하기 화면으로 이동하기
	@GetMapping("hireLocation")
	public String hireLocation(
			Model model) {
		
		List<Dosi> dosiList = service.selectDosi();
		model.addAttribute("dosiList", dosiList);
		
		return "hire/hireLocation";
	}
	

	
	
	
	//시/도에 따라 시군구 조회하기
	@GetMapping("/selectSigungu")
	@ResponseBody
	public List<Sigungu> selectSigungu(
			@RequestParam("dosiName") String dosiName){
		
		List<Sigungu> sigunguList = service.selectSigungu(dosiName);
		
		return sigunguList;
	}
	
	
	//동읍면 조회하기
	@GetMapping("/selectDong")
	@ResponseBody
	public List<Dong> selectDong(
			@RequestParam("sigunguName") String sigunguName){
		
		List<Dong> dongList = service.selectDong(sigunguName);
		
		return dongList;
		
	}
	
	
	
	//업직종별 공고 조회하기 화면으로 이동하기
	@GetMapping("hireKind")
	public String hireKind(
			Model model) {
		
		List<Hire> typeList = service.selectKind();
		model.addAttribute("typeList", typeList);
		
		return "hire/hireKind";
	}
	
	
	
	//지원하기 팝업창으로 이동 -> hireNo 사용하기 위해 실제 페이지로 이동하기 전에 번호만 얻어오는 메서드
	@GetMapping("hireApply/{hireNo}")
	public String hireApply(
			@PathVariable("hireNo") int hireNo,
			Model model) {
		Hire hireInfo = service.hireInfo(hireNo);
		
		if(hireInfo != null) {
			model.addAttribute("hireInfo", hireInfo);
		}
	    return "/hire/hireApply"; // 이동할 URL로 포워딩
	}

	
	//지역별 공고 조회해오기
	@PostMapping("locationHireList")
	@ResponseBody
	public Map<String,Object> locationHireList(
			@RequestBody Map<String, Object> map
			){
		
		return service.locationHireList(map);
	}
	
	//업직종별 공고 조회해오기
	@PostMapping("kindHireList")
	@ResponseBody
	public Map<String,Object> kindHireList(
			@RequestBody Map<String, Object> map
			){
		
		return service.kindHireList(map);
	}
	

	// 지원서 작성
	@PostMapping("hireApply/{hireNo}")
	public void hireApply(
	        @PathVariable("hireNo") int hireNo,
	        @SessionAttribute("loginMember") Member loginMember,
	        Hire hire,
	        RedirectAttributes ra,
	        Model model,
	        HttpServletResponse response
	) throws IOException{        
	    int memberNo = loginMember.getMemberNo();
	    
	    int result = service.hireApply(memberNo, hire, hireNo);
	    
	    String message = "지원이 완료되었습니다.";
	    ra.addFlashAttribute("message", message);
	    
	    response.setContentType("text/html; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    
	    PrintWriter out = response.getWriter();
	    out.println("<script type='text/javascript'>");
	    out.println("alert('지원이 완료되었습니다.');");
	    out.println("window.close();");
	    out.println("</script>");
	    out.flush();
	}
	

	//지원서 상세조회(인서-테스트)
	@GetMapping("hireApplyDetail")
	public String hireApplyDetail() {
		return "hire/hireApplyDetail";
	}

	//공고 수정 버튼 클릭 시 화면 이동
	@GetMapping("update/{hireNo:[0-9]+}")
	public String hireUpdate(
			@PathVariable("hireNo") int hireNo,
			Model model) {
		Hire hire = service.allHire(hireNo);
		List<String> dayList = Arrays.asList(hire.getWorkDay().split(","));
		model.addAttribute("hire", hire);
		model.addAttribute("dayList", dayList);
		
		return "hire/hireUpdate";
	}
	
	//공고 수정
	@PostMapping("hireUpdate")
	public String hireUpdate(
			Hire hire,
			Model model,
			@SessionAttribute("loginShop") Shop loginShop,
			RedirectAttributes ra
			) {
		int shopNo = loginShop.getShopNo();
		hire.setShopNo(shopNo);
		
		int hireNo = service.hireUpdate(hire);
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
			message="공고가 성공적으로 수정되었습니다.";
			path="/hire/"+hireNo; //등록 성공한 공고 상세조회 페이지로 
		}else {
			//공고 등록 실패 시
			message="공고 수정에 실패하였습니다.";
			path="/hire/"+hireNo;
		}
		ra.addFlashAttribute("message", message);
		return "redirect:"+path; 
	}
	// 지원하기 클릭 시 지원을 했는지 여부 판단하는 비동기 요청
	@GetMapping("hireApplyCheck/{hireNo:[0-9]+}")
	@ResponseBody
	public int hireApplyCheck(
			@PathVariable("hireNo") int hireNo,
	        @SessionAttribute("loginMember") Member loginMember) {
		int memberNo = loginMember.getMemberNo();
		
		int result = service.hireApplyCheck(hireNo, memberNo);
		
		if(result >= 1) {
			return 1;
		}
		
		return 0;
	}
}


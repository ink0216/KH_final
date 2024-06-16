package kh.em.albaya.resume.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.em.albaya.hire.model.service.HireService;
import kh.em.albaya.location.dto.Dosi;
import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.resume.model.dto.Resume;
import kh.em.albaya.resume.model.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//ink0216@naver.com 비번 pass0101로 바뀜!!!!!!
@Controller
@RequestMapping("resume")
@RequiredArgsConstructor
@Slf4j
public class ResumeController {
	private final ResumeService service;
	private final HireService hireService;
	/*RESUME테이블
	 * - RESUME_NO, MEMBER_NO, CAREER, RESUME_TITLE, INTRODUCE, IMAGE, RESUME_STATUS
	 * 
	 * LICENSE 자격증
	 * - LICENSE_NO, RESUME_NO, LICENSE_FROM, LICENSE_DATE, LICENSE_NAME, LICENSE_SCORE
	 * 
	 * RESUME_LOCATION - DONG
	 *  - HOPE_NO, RESUME_NO, DONG_NO
	 *  
	 * CAREER
	 * RESUME_WORK (희망직종) - WORK_TYPE
	 * 
	 * RESUME_EDUCATION : 졸업, 재학, 휴학, 중퇴, 수료
	 * - RESUME_EDUCATION_NO, RESUME_NO, EDUCATION_STATUS_NO, EDUCATION_NO, 
	 *   SCHOOL_NAME,SCHOOL_PERIOD
	 *   
	 * EDUCATION : 초, 중,고,대
	 *  - EDUCATION_NO, EDUCATION_NAME
	 *  
	 * EDUCATION_STATUS : 졸업,재학,휴학
	 *  - EDUCATION_STATUS_NO, EDUCATION_STATUS_NAME
	 *
	 * */
	@GetMapping("resumeWrite")
	public String resumeWrite(
			@SessionAttribute("loginMember") Member loginMember,
			Model model,
			RedirectAttributes ra
			) {
		//그 회원이 몇 개의 이력서 작성했는지 조회해서 5개라면 막기
		int memberNo = loginMember.getMemberNo();
		int resumeCount = service.resumeCount(memberNo);
		if(resumeCount >= 5) {
			ra.addFlashAttribute("message", "회원 당 최대 5개의 이력서만 작성 가능합니다. ");
			return "redirect:/resume/resumeList";
			//이력서 목록 페이지
		}
		//이력서 작성
		String[] addressList = loginMember.getMemberAddress().split("\\^\\^\\^");
		String address = addressList[1];
		model.addAttribute("address", address);
		List<Resume> semiResumeList = service.semiResumeList(loginMember.getMemberNo()); // 임시저장인 이력서 조회해오기
		model.addAttribute("semiResumeList", semiResumeList);
		
		//학력 사항 조회해서 화면 만들기
		List<Resume> educationList = service.educationList();
		model.addAttribute("educationList",educationList);
		
		//시도 조회해서 화면 만들기
		List<Dosi> dosiList = hireService.selectDosi();
		model.addAttribute("dosiList", dosiList);
		
		//직종 조회해서 화면 만들기
		List<Resume> workList = service.workList();
			List<Resume> workList1 = workList.subList(0, 3);
			List<Resume> workList2 = workList.subList(3, 6);
			List<Resume> workList3 = workList.subList(6, workList.size());
		model.addAttribute("workList1", workList1);
		model.addAttribute("workList2", workList2);
		model.addAttribute("workList3", workList3);
		
		//학력 상태 조회해서 화면 만들기
		List<Resume> statusList = service.statusList();
		model.addAttribute("statusList", statusList);
		//educationStatusNo, educationStatusName
		
		
		return "member/resume";
	} 
	@PostMapping("resumeWrite")
	public String resumeWrite (
			/*------------------------------------------------------------------------------
			/*넘어오는 모든 name값들:
			 * image, resumeTitle, introduce, educationNo, 
			 * //초 : schoolName, educationStatusNo, schoolStartDate, schoolEndDate
			//중 : 동일
			//고 : 동일
			//대 : schoolStartDate, schoolEndDate
			 * 경력 : companyName*, startDate*, endDate* ****0
			 * 자격증 : licenseName*, licenseFrom*, licenseScore*, licenseDate* ****0
			 * dongNo(여러 개 또는 0)* ****0
			 * career (신입1, 경력2)
			 * typeName(직종명)* ****0
			 * resumeStatus(0저장, 1임시저장)
			 * ------------------------------------------------------------------------------
			 * 
			 * */
			Resume resume, //하나씩만 들어오는 것들
			@RequestParam(value="companyName", required=false) List<String> companyNameList,
			@RequestParam(value="startDate", required=false) List<String> startDateList,
			@RequestParam(value="endDate", required=false) List<String> endDateList,
			
			@RequestParam(value="licenseName", required=false) List<String> licenseNameList,
			@RequestParam(value="licenseFrom", required=false) List<String> licenseFromList,
			@RequestParam(value="licenseScore", required=false) List<Integer> licenseScoreList,
			@RequestParam(value="licenseDate", required=false) List<String> licenseDateList,
			
			@RequestParam(value="dongNo", required=false) List<Integer> dongNoList,
			@RequestParam(value="typeName", required=false) List<String> typeNameList,
			RedirectAttributes ra,
			@SessionAttribute("loginMember") Member loginMember
			) throws IllegalStateException, IOException
	{
		//이력서 작성 성공 시 일단 메인페이지로 가면서 메시지 띄우고 필요하면 나중에 이력서 상세조회 페이지로 변경하기
		int result = service.resumeWrite(resume, companyNameList, startDateList, endDateList, licenseNameList, 
				licenseFromList, licenseScoreList, licenseDateList, dongNoList, typeNameList
				,loginMember);
		
		String message=null;
		if(result > 0) {
			ra.addFlashAttribute("message", "이력서가 성공적으로 저장되었습니다.");
			return "redirect:/";
		}
		//작성 실패 시 
		ra.addFlashAttribute("message", "이력서 저장에 실패하였습니다.");
		return "redirect:/";
	}
	@GetMapping("resumeList")
	public String getResumeList(
			@SessionAttribute("loginMember") Member loginMember,
			Model model
			) {
		int memberNo = loginMember.getMemberNo();
		List<Resume> resumeList = service.allResumeList(memberNo);
//		List<Resume> semiList = new ArrayList<>();
//		List<Resume> doneList = new ArrayList<>();
//		for(int i=0;i<resumeList.size();i++) {
//			if(resumeList.get(i).getResumeStatus() == 1) {
//				semiList.add(resumeList.get(i));
//			}else {
//				doneList.add(resumeList.get(i));
//			}
//			
//		}
//		for(int i=0;i<semiList.size();i++) {
//			
//		}
		model.addAttribute("resumeList", resumeList);
		return "member/resumeList";
	}
	
	@GetMapping("resumeProfile")
	public String getResumeProfile(
			@RequestParam("resumeNo") int resumeNo,
			Model model
			) {
		//이력서 내용 다 조회
		//학력,희망근무지(시도군구동), 희망업직종, 경력사항, 자격증
		Map<String, Object> map = service.resumeDetail(resumeNo);
		/*map.put("resume", resume);
		map.put("careerList", careerList);
		map.put("licenseList", licenseList);
		map.put("locationList", locationList);
		map.put("workList", workList);
		map.put("educationDetail", educationDetail);
		 * 
		 * 
		 * */
		model.addAttribute("resume", (Resume)map.get("resume"));
		model.addAttribute("careerList", (List<Resume>)map.get("careerList"));
		model.addAttribute("licenseList", (List<Resume>)map.get("licenseList"));
		model.addAttribute("locationList", (List<Resume>)map.get("locationList"));
		model.addAttribute("workList", (List<Resume>)map.get("workList"));
		Resume educationInfo = (Resume)map.get("educationDetail");
		//,로 잘랐을 때 여섯 번째(5번 인덱스)에 ~이 있다.
		//2일 때에는 2,7
		//4이면 3,8번 인덱스
		String education = null;
		for(int i=1;i<7;i++) {
			if(((Resume)map.get("educationDetail")).getEducationNo().equals(String.valueOf(i))) {
				 education = ((Resume)map.get("educationDetail")).getSchoolPeriod().split(",")[i-1]
						+ " ~ " + ((Resume)map.get("educationDetail")).getSchoolPeriod().split(",")[i+4];
			}
		}
		 String fullImagePath = ((Resume)map.get("resume")).getImgPath() + ((Resume)map.get("resume")).getImgRename();
	        model.addAttribute("fullImagePath", fullImagePath);
		model.addAttribute("education", education);
		model.addAttribute("educationDetail", (Resume)map.get("workList"));
		return "member/resumeProfile";
	}
	
	//이력서 삭제
	@GetMapping("resumeDelete")
	public String resumeDelete(
			@RequestParam("resumeNo") int resumeNo,
			RedirectAttributes ra) {
		int result = service.resumeDelete(resumeNo);
		if(result>0) ra.addFlashAttribute("message", "이력서가 삭제되었습니다.");
		else ra.addFlashAttribute("message", "이력서 삭제가 실패하였습니다.");
		return "redirect:/resume/resumeList";
		
	}
	
	//이력서 상세 조회
	@GetMapping("resumeDetail")
	public String resumeDetail(
			@RequestParam("resumeNo") int resumeNo,
			Model model
			) {
		Map<String, Object> map = service.resumeDetail(resumeNo);
		model.addAttribute("resume", (Resume)map.get("resume"));
		model.addAttribute("careerList", (List<Resume>)map.get("careerList"));
		model.addAttribute("licenseList", (List<Resume>)map.get("licenseList"));
		model.addAttribute("locationList", (List<Resume>)map.get("locationList"));
		model.addAttribute("workList", (List<Resume>)map.get("workList"));
		model.addAttribute("educationDetail", (Resume)map.get("educationDetail"));
		return "member/resumeProfile";
	}
}

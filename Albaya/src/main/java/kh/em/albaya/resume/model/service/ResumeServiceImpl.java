package kh.em.albaya.resume.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.em.albaya.common.util.Utility;
import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.resume.model.dto.Resume;
import kh.em.albaya.resume.model.mapper.ResumeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor	
@PropertySource("classpath:/config.properties")
@Slf4j
public class ResumeServiceImpl implements ResumeService {
	private final ResumeMapper mapper;
	
	@Value("${my.resume.web-path}")
	private String webPath; //이 값을 얻어와서 여기에 저장하겠다
	
	@Value("${my.resume.folder-path}")
	private String folderPath;//이 값을 얻어와서 여기에 저장하겠다
	
	//해당 회원이 임시저장했던 이력서 조회해오기
	@Override
	public List<Resume> semiResumeList(int memberNo) {
		return mapper.semiResumeList(memberNo);
	}
	
	//학력 사항 조회해서 화면 만들기
	@Override
	public List<Resume> educationList() {
		return mapper.educationList();
	}
	
	//직종 조회해서 화면 만들기
	@Override
	public List<Resume> workList() {
		return mapper.workList();
	}
	
	//학력 상태 조회해서 뫄념 나들기
	@Override
	public List<Resume> statusList() {
		return mapper.statusList();
	}
	
	//이력서 작성
	@Override
	public int resumeWrite(Resume resume, List<String> companyNameList, List<String> startDateList,
			List<String> endDateList, List<String> licenseNameList, List<String> licenseFromList,
			List<Integer> licenseScoreList, List<String> licenseDateList, List<Integer> dongNoList,
			List<String> typeNameList, Member loginMember) throws IllegalStateException, IOException {
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
		Integer educationStatusNo = resume.getEducationStatusNo();
		resume.setEducationStatusNo(educationStatusNo);
		
		int memberNo = loginMember.getMemberNo();
		resume.setMemberNo(memberNo);
		
		resume.setImgPath(webPath);
		
		String imgOriginalName = resume.getImage().getOriginalFilename();
		resume.setImgOriginalName(imgOriginalName);
		
		String rename = Utility.fileRename(imgOriginalName);
		resume.setImgRename(rename);
		
		int result = mapper.resume(resume); //사진 제외한 것만 INSERT
		if(result==0) {
			return 0; //실패한 경우
		}
		int resumeNo = resume.getResumeNo(); //위에서 삽입 성공 시 resume의 얕은복사여서 
		//삽입 성공된 resume의 resumeNo값도 세팅된다.
		/*List<String> companyNameList, List<String> startDateList, List<String> endDateList, 
		 * List<String> licenseNameList, List<String> licenseFromList, List<Integer> licenseScoreList, List<String> licenseDateList, 
		 * List<Integer> dongNoList,
			List<String> typeNameList,
		 * */
		// CAREER테이블 INSERT
		for(int i=0;i<companyNameList.size();i++) {
			resume.setCompanyName(companyNameList.get(i));
			resume.setStartDate(startDateList.get(i));
			resume.setEndDate(endDateList.get(i));
			
			result=mapper.career(resume);
			if(result==0) throw new RuntimeException("career insert error");
		}
		
		if(result==0) return 0;
		//성공시
		
		// LICENSE 테이블 INSERT
		for(int i=0;i<licenseNameList.size();i++) {
			resume.setLicenseName(licenseNameList.get(i));
			resume.setLicenseFrom(licenseFromList.get(i));
			resume.setLicenseScore(licenseScoreList.get(i));
			resume.setLicenseDate(licenseDateList.get(i));
			
			result = mapper.license(resume);
			if(result==0) throw new RuntimeException("license insert error");
		}
		if(result==0) return 0;
		//성공시
		
		//RESUME_LOCATION 테이블 INSERT
		for(int i=0;i<dongNoList.size();i++) {
			resume.setDongNo(dongNoList.get(i));
			
			result = mapper.resumeLocation(resume);
			if(result==0) throw new RuntimeException("resumeLocation insert error");
		}
		if(result==0) return 0;
		//성공시
		
		//RESUME_WORK 테이블에 INSERT
		for(int i=0;i<typeNameList.size();i++) {
			String typeName = typeNameList.get(i);
			int typeNo = mapper.typeNo(typeName);
			resume.setTypeNo(typeNo);
			
			result = mapper.resumeWork(resume);
			if(result==0) throw new RuntimeException("resumeWork insert error");
		}
		if(result==0) return 0;
		//성공시
		
		//RESUME_EDUCATION 테이블에 INSERT
		String schoolPeriod = resume.getSchoolStartDate()+" ~ "+resume.getSchoolEndDate();
		resume.setSchoolPeriod(schoolPeriod);
			result = mapper.resumeEducation(resume);
		
		if(result==0) throw new RuntimeException("resumeEducation insert error");
		if(result==0) return 0;
		//성공시
		resume.getImage().transferTo(new File(folderPath+rename));
		return 1;
	}
}

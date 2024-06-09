package kh.em.albaya.resume.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.em.albaya.resume.model.dto.Resume;
import kh.em.albaya.resume.model.mapper.ResumeMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor	
public class ResumeServiceImpl implements ResumeService {
	private final ResumeMapper mapper;
	
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
			List<String> typeNameList) {
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
		int result = mapper.resume(resume); //사진 제외한 것만 INSERT
		if(result==0) {
			return 0; //실패한 경우
		}
		int resumeNo = resume.getResumeNo(); //위에서 삽입 성공 시 resume의 얕은복사여서 
		//삽입 성공된 resume의 resumeNo값도 세팅된다.
		
		return 0;
	}
}

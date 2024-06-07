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
}

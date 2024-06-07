package kh.em.albaya.resume.model.service;

import java.util.List;

import kh.em.albaya.resume.model.dto.Resume;

public interface ResumeService {

	/**해당 회원이 작성했던 임시저장 이력서 조회해오기
	 * @param memberNo
	 * @return
	 */
	List<Resume> semiResumeList(int memberNo);

	/**학력 사항 조회해서 화면 만들기
	 * @return
	 */
	List<Resume> educationList();

}

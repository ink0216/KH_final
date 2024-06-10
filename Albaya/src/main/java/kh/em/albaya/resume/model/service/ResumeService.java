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

	/**직종 조회해서 화면 만들기
	 * @return
	 */
	List<Resume> workList();

	/**학력 상태 조회해서 화면 만들기
	 * @return
	 */
	List<Resume> statusList();

	/**이력서 작성
	 * @param resume
	 * @param companyNameList
	 * @param startDateList
	 * @param endDateList
	 * @param licenseNameList
	 * @param licenseFromList
	 * @param licenseScoreList
	 * @param licenseDateList
	 * @param dongNoList
	 * @param typeNameList
	 * @return
	 */
	int resumeWrite(Resume resume, List<String> companyNameList, List<String> startDateList, List<String> endDateList,
			List<String> licenseNameList, List<String> licenseFromList, List<Integer> licenseScoreList,
			List<String> licenseDateList, List<Integer> dongNoList, List<String> typeNameList);

}

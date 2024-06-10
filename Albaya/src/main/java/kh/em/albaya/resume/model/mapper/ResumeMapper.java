package kh.em.albaya.resume.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kh.em.albaya.resume.model.dto.Resume;

@Mapper
public interface ResumeMapper {

	/**해당 회원이 임시저장했던 이력서 조회해오기
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

	/**하나만 들어가는 것만 insert
	 * @param resume
	 * @return
	 */
	int resume(Resume resume);

	/**CAREER 테이블 INSERT
	 * @param resume
	 * @return
	 */
	int career(Resume resume);

	/**LICENSE 테이블 INSERT
	 * @param resume
	 * @return
	 */
	int license(Resume resume);

	/**RESUME_LOCATION 테이블에 INSERT
	 * @param resume
	 * @return
	 */
	int resumeLocation(Resume resume);

	/**typeName으로 typeNo 찾기
	 * @param typeName
	 * @return
	 */
	int typeNo(String typeName);

	/**RESUME_WORK 테이블에 INSERT
	 * @param resume
	 * @return
	 */
	int resumeWork(Resume resume);

	/**RESUME_EDUCATION 테이블에 INSERT
	 * @param resume
	 * @return
	 */
	int resumeEducation(Resume resume);

	

	
}

package kh.em.albaya.resume.model.mapper;

import java.util.List;

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

	int resume(Resume resume);

	
}

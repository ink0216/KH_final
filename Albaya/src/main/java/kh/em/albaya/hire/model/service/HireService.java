package kh.em.albaya.hire.model.service;

import java.util.List;
import java.util.Map;

import kh.em.albaya.hire.model.dto.Hire;

public interface HireService {

	/**공고 INSERT
	 * @param hire
	 * @return
	 */
	int hireWrite(Hire hire);

	/**공고 상세조회
	 * @param hireNo
	 * @return
	 */
	Hire detailHire(int hireNo);

	Map<String,Object> selectHireList(int cp);

	
}

package kh.em.albaya.hire.model.service;

import kh.em.albaya.hire.model.dto.Hire;

public interface HireService {

	/**공고 INSERT
	 * @param hire
	 * @return
	 */
	int hireWrite(Hire hire);

	/**typeNo 찾기
	 * @param hire
	 * @return
	 */
	int typeNo(Hire hire);

}

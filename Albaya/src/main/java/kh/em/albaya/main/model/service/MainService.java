package kh.em.albaya.main.model.service;

import java.util.List;

import kh.em.albaya.hire.model.dto.Hire;

public interface MainService {

	/**공고 전체 리스트 조회
	 * @return
	 */
	List<Hire> hireList();

}

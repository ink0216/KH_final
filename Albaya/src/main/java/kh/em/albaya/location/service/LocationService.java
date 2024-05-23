package kh.em.albaya.location.service;

import java.util.List;

import kh.em.albaya.location.dto.Dong;
import kh.em.albaya.location.dto.Dosi;
import kh.em.albaya.location.dto.Sigungu;

public interface LocationService {

	/**위치 데이터 모두 db에 insert
	 * @param dosiList
	 * @param sigunguList
	 * @param dongList
	 * @return
	 */
	int insertDb(List<Dosi> dosiList, List<Sigungu> sigunguList, List<Dong> dongList);

}

package kh.em.albaya.location.mapper;

import org.apache.ibatis.annotations.Mapper;

import kh.em.albaya.location.dto.Dong;
import kh.em.albaya.location.dto.Dosi;
import kh.em.albaya.location.dto.Sigungu;

@Mapper
public interface LocationMapper {

	/**도/시 insert
	 * @param dosi
	 * @return
	 */
	int insertDosi(Dosi dosi);

	/**시/군/구 insert
	 * @param sigungu
	 * @return
	 */
	int insertSigungu(Sigungu sigungu);

	/**동 insert
	 * @param dong
	 * @return
	 */
	int insertDong(Dong dong);

}

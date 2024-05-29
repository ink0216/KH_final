package kh.em.albaya.hire.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.location.dto.Dosi;

@Mapper
public interface HireMapper {

	/**typeName에 따른 typeNo 조회
	 * @param typeName
	 * @return
	 */
	int hireTypeNo(String typeName);

	

	/**INSERT
	 * @param hire
	 * @return
	 */
	int insertWrite(Hire hire);



	/**시군구동이 모두 일치하는 동 번호 조회
	 * @param hireLocation
	 * @return
	 */
	int hireDongNo(Map<String, String> hireLocation);



	/**공고 상세 조회
	 * @param hireNo
	 * @return
	 */
	Hire detailHire(int hireNo);


	int selectListCount();

	List<Hire> selectHireList(Object object, RowBounds rowBounds);



	
	/**
	 * 시도 조회하기
	 * @return
	 */
	List<Dosi> selectDosi();




	

	
}

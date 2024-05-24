package kh.em.albaya.hire.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import kh.em.albaya.hire.model.dto.Hire;

@Mapper
public interface HireMapper {

	/**HIRE 테이블에만 INSERT
	 * @param hire
	 * @return
	 */
	public int hireInsert(Hire hire);

	/**typeNo 찾기
	 * @param hire
	 * @return
	 */
	public int typeNo(Hire hire);

	/**dongNo 찾기
	 * @param hire
	 * @return
	 */
	public int dongNo(Hire hire);

}

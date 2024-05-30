package kh.em.albaya.main.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kh.em.albaya.hire.model.dto.Hire;

@Mapper
public interface MainMapper {

	/**공고 전체 리스트 조회
	 * @return
	 */
	List<Hire> hireList();

}

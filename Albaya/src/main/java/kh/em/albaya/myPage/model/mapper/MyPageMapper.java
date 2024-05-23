package kh.em.albaya.myPage.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

	int myPageCheckPw(Map<String, Object> map);

	String findEncPw(Map<String, Object> map);
	
}

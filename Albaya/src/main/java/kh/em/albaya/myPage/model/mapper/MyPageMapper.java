package kh.em.albaya.myPage.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

	int myPageCheckPw(Map<String, Object> map);

	String findEncPw(Map<String, Object> map);

	int deleteMember(Map<String, Object> map);

	int updateMemberPw(Map<String, Object> map);

	String findMemberPw(int memberNo);

	String findShopPw(int shopNo);
	
	int updateShopPw(Map<String, Object> map);

	String findShopEncPw(Map<String, Object> map);

	int deleteShop(Map<String, Object> map);

	int myPageCheckShopPw(Map<String, Object> map);

}

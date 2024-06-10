package kh.em.albaya.myPage.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.shop.model.dto.Shop;

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

	int changeProfile(Shop shop);

	int countHireApply(int memberNo);

	int memberInfoUpdate(Member member);

	int findDongNo(Map<String, Object> map);

	String loginShopAddress(int dongNo);

	int shopInfoUpdate(Shop shop);
}

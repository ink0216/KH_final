package kh.em.albaya.myPage.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kh.em.albaya.myPage.model.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements myPageService{
	private final MyPageMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;

	@Override
	public int myPageCheckPw(int memberNo, String memberEmail, String memberPw) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberNo", memberNo);
		map.put("memberEmail", memberEmail);
		
		String encPw = mapper.findEncPw(map);
		
		if(!bcrypt.matches(memberPw, encPw)) {
			return 0;
		}
		
		return mapper.myPageCheckPw(map);
	}
	
	@Override
	public int deleteMember(int memberNo, String memberEmail) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("memberNo", memberNo);
		map.put("memberEmail", memberEmail);
		
		return mapper.deleteMember(map);
	}
	
	@Override
	public int findMemberPw(int memberNo, String curPassword, String newPassword) {
		
		String memberPw = mapper.findMemberPw(memberNo);
		
		Map<String, Object> map = new HashMap<>();
		
		String encPw = bcrypt.encode(newPassword);
		
		map.put("memberNo", memberNo);
		map.put("encPw", encPw);
		
		if(!bcrypt.matches(curPassword, memberPw)) {
			return 0;
		}
		
		int result = mapper.updateMemberPw(map);
		
		return result;
	}
	
	@Override
	public int findShopPw(int shopNo, String curPassword, String newPassword) {
		String shopPw = mapper.findShopPw(shopNo);
		
		Map<String, Object> map = new HashMap<>();
		
		String encPw = bcrypt.encode(newPassword);
		
		map.put("shopNo", shopNo);
		map.put("encPw", encPw);
		
		if(!bcrypt.matches(curPassword, shopPw)) {
			return 0;
		}
		
		int result = mapper.updateShopPw(map);
		
		return result;
	}
	
	@Override
	public int myPageCheckShopPw(int shopNo, String shopEmail, String shopPw) {
		Map<String, Object> map = new HashMap<>();
		map.put("shopNo", shopNo);
		map.put("shopEmail", shopEmail);
		
		String encPw = mapper.findShopEncPw(map);
		
		if(!bcrypt.matches(shopPw, encPw)) {
			return 0;
		}
		
		return mapper.myPageCheckPw(map);
	}
	
	@Override
	public int deleteShop(int shopNo, String shopEmail) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("shopNo", shopNo);
		map.put("shopEmail", shopEmail);
		
		return mapper.deleteShop(map);
	}
}

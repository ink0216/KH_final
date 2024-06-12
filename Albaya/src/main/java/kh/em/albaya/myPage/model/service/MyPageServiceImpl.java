package kh.em.albaya.myPage.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kh.em.albaya.common.util.Utility;
import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.myPage.model.mapper.MyPageMapper;
import kh.em.albaya.shop.model.dto.Shop;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements myPageService{
	private final MyPageMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;
	
	@Value("${my.profile.web-path}")
	private String profileWebPath;
	
	@Value("${my.profile.folder-path}")
	private String profileFolderPath;

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
		
		return mapper.myPageCheckShopPw(map);
	}
	
	@Override
	public int deleteShop(int shopNo, String shopEmail) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("shopNo", shopNo);
		map.put("shopEmail", shopEmail);
		
		return mapper.deleteShop(map);
	}
	
	@Override
	public int changeProfile(Shop shop, MultipartFile profileImg) throws IllegalStateException, IOException {
		String updatePath = null;
		
		String rename = null;
		if(!profileImg.isEmpty()) {
			// updatePath
			
			rename = Utility.fileRename(profileImg.getOriginalFilename());
			
			// /myPage/profile/변경된파일명.jpg
			updatePath = profileWebPath + rename;
		}
		
		shop.setShopProfile(updatePath);

		Map<String, Object> map = new HashMap<>();
		
		map.put("shop", shop);
		map.put("shopProfile", updatePath);
		
		shop.setShopProfile(updatePath);
		
		if(shop.getShopProfile() == null){
			return 1;
		}
		
		int result = mapper.changeProfile(shop);
		
		if(result >= 1) {
			if(!profileImg.isEmpty()) {				
				profileImg.transferTo(new File(profileFolderPath + rename));
			}
			return 1;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public int countHireApply(int memberNo) {
		return mapper.countHireApply(memberNo);
	}
	
	@Override
	public int memberInfoUpdate(Member member, String[] memberAddress) {
		
		
		if(!member.getMemberAddress().equals(",,")) {
			
			String address = String.join("^^^", memberAddress);
			
			member.setMemberAddress(address);
		}
		else {
			member.setMemberAddress(null);
		}
		return mapper.memberInfoUpdate(member);
	}
	
	@Override
	public int shopInfoUpdate(Shop shop) {		
		Map<String, Object> map = new HashMap<>();
		
		if(shop.getDosiName() != null) {
			String dosiName = shop.getDosiName();
			String sigunguName = shop.getSigunguName();
			String dongName = shop.getDongName();
			
			map.put("dosiName", dosiName);
			map.put("sigunguName", sigunguName);
			map.put("dongName", dongName);
			
			int dongNo = mapper.findDongNo(map);
			shop.setDongNo(dongNo);
			
			String fullAddress = mapper.loginShopAddress(dongNo);
			
			shop.setFullAddress(fullAddress);
		}
		
		return mapper.shopInfoUpdate(shop);
	}
	@Override
	public Map<String, Object> applyList(int memberNo) {
		Map<String, Object> applyList = mapper.applyList(memberNo);
		int applyCount = mapper.applyCount(memberNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("applyList", applyList);
		map.put("applyCount", applyCount);
		return map;
	}
	
}

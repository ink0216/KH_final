package kh.em.albaya.shop.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.shop.model.dto.Shop;
import kh.em.albaya.shop.model.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopServiceImpl implements ShopService{
	private final ShopMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;
	
	@Override
	public int checkEmail(String shopEmail) {
		return mapper.checkEmail(shopEmail);
	}
	
	@Override
	public int checkBrn(String shopBrn) {
		return mapper.checkBrn(shopBrn);
	}
	
	@Override
	public int signup(Shop shop, MultipartFile profileImg) {
		
		Map<String, Object> map = new HashMap<>();
		
		String dosiName = shop.getDosiName();
		String sigunguName = shop.getSigunguName();
		String dongName = shop.getDongName();
		
		map.put("dosiName", dosiName);
		map.put("sigunguName", sigunguName);
		map.put("dongName", dongName);
		
		int dongNo = mapper.findDongNo(map);
		shop.setDongNo(dongNo);
		
		String shopPw = shop.getShopPw();
		String encPw = bcrypt.encode(shopPw);
		shop.setShopPw(encPw);
		
		
		int result = mapper.signup(shop, profileImg);
		
		if(result < 1) {
			return 0;
		}
		
		return result;
	}
	
	@Override
	public Shop login(Shop inputShop) {
		Shop loginShop = mapper.login(inputShop.getShopEmail());
		
		if(loginShop == null) {
			return null;
		}
		
		if(!bcrypt.matches(inputShop.getShopPw(), loginShop.getShopPw())) {
			return null;
		}
		
		loginShop.setShopPw(null);
		
		return loginShop;
	}
	
	@Override
	public String loginShopAddress(int shopDongNo) {
		return mapper.loginShopAddress(shopDongNo);
	}
}

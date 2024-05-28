package kh.em.albaya.shop.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kh.em.albaya.shop.model.dto.Shop;
import kh.em.albaya.shop.model.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
	public int signup(Shop shop) {
		
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
		
		
		int result = mapper.signup(shop);
		
		if(result < 1) {
			return 0;
		}
		
		return result;
	}
}

package kh.em.albaya.shop.model.service;


import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.shop.model.dto.Shop;

public interface ShopService{

	int checkEmail(String shopEmail);

	int checkBrn(String shopBrn);

	int signup(Shop shop, MultipartFile profileImg) throws IllegalStateException, IOException;

	Shop login(Shop inputShop);

	String loginShopAddress(int shopDongNo);
}

package kh.em.albaya.shop.model.service;

import kh.em.albaya.shop.model.dto.Shop;

public interface ShopService{

	int checkEmail(String shopEmail);

	int checkBrn(String shopBrn);

	int signup(Shop shop);
}

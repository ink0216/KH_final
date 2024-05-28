package kh.em.albaya.shop.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kh.em.albaya.shop.model.dto.Shop;

@Mapper
public interface ShopMapper {

	int checkEmail(String shopEmail);

	int checkBrn(String shopBrn);

	int findDongNo(Map<String, Object> map);

	int signup(Shop shop);

	
}

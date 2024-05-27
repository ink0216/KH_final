package kh.em.albaya.shop.model.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper {

	int checkEmail(String memberEmail);

}

package kh.em.albaya.shop.model.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kh.em.albaya.shop.model.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{
	private final ShopMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;
	
}

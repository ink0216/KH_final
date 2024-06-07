package kh.em.albaya.myPage.model.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.shop.model.dto.Shop;

public interface myPageService {

	int myPageCheckPw(int memberNo, String memberEmail, String memberPw);

	int deleteMember(int memberNo, String memberEmail);

	int findMemberPw(int memberNo, String curPassword, String newPassword);

	int findShopPw(int shopNo, String curPassword, String newPassword);

	int myPageCheckShopPw(int shopNo, String shopEmail, String shopPw);

	int deleteShop(int shopNo, String shopEmail);

	int changeProfile(Shop shop, MultipartFile profileImg) throws IllegalStateException, IOException;

	int countHireApply(int memberNo);

	int memberInfoUpdate(Member member, String[] memberAddress);
}

package kh.em.albaya.myPage.model.service;

public interface myPageService {

	int myPageCheckPw(int memberNo, String memberEmail, String memberPw);

	int deleteMember(int memberNo, String memberEmail);

	int findMemberPw(int memberNo, String curPassword, String newPassword);

	int findShopPw(int shopNo, String curPassword, String newPassword);

	int myPageCheckShopPw(int shopNo, String shopEmail, String shopPw);

	int deleteShop(int shopNo, String shopEmail);

}

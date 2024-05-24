package kh.em.albaya.myPage.model.service;

public interface myPageService {

	int myPageCheckPw(int memberNo, String memberEmail, String memberPw);

	int deleteMember(int memberNo, String memberEmail);

}

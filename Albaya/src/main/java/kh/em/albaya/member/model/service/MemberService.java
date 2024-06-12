package kh.em.albaya.member.model.service;

import java.util.List;
import java.util.Map;

import kh.em.albaya.member.model.dto.Member;

public interface MemberService {

	/** 회원가입
	 * @param member
	 * @return result
	 */
	int signup(Member member, String[] memberAddress);

	/** 로그인
	 * @param inputMember
	 * @return 
	 */
	Member login(Member inputMember);

	/** 아이디 찾기
	 * @param member
	 * @return
	 */
	int findId(Member member);

	String findMemberId(Member member);

	int memberPwCount(Member member);

	int changePw(String newPassword, String memberEmail);

	Member findMemberByNo(int memberNo);

	int checkEmail(String memberEmail);

	int checkTel(String memberPhoneNumber);

	boolean checkPw(String memberPw);


}

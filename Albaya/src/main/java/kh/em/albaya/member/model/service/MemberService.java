package kh.em.albaya.member.model.service;

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

}

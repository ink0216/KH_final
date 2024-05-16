package kh.em.albaya.member.model.mapper;

import kh.em.albaya.member.model.dto.Member;

public interface MemberMapper {

	/** 회원가입
	 * @param member
	 * @return result
	 */
	int signup(Member member);

}

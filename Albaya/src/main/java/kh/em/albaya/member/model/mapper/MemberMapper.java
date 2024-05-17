package kh.em.albaya.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import kh.em.albaya.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	/** 회원가입
	 * @param member
	 * @return result
	 */
	int signup(Member member);

	/** 로그인
	 * @param memberEmail
	 * @return
	 */
	Member login(String memberEmail);

}

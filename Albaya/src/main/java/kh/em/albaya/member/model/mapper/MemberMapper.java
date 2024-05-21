package kh.em.albaya.member.model.mapper;

import java.util.List;
import java.util.Map;

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

	/** 아이디 찾기
	 * @param member
	 * @return
	 */
	int memberCount(Member member);

	String findMemberId(Member member);

	int memberPwCount(Member member);

	int changePw(Map<String, Object> map);


	
	
}

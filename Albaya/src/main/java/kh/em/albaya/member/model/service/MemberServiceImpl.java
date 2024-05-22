package kh.em.albaya.member.model.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;
	
	@Override
	public int signup(Member member, String[] memberAddress) {
		String encPw = bcrypt.encode(member.getMemberPw());
		
		member.setMemberPw(encPw);
		
		
		if(!member.getMemberAddress().equals(",,")) {
		
			String address = String.join("^^^", memberAddress);
			
			member.setMemberAddress(address);
		}
		else {
			member.setMemberAddress(null);
		}
		
		return mapper.signup(member);
	}
	
	@Override
	public Member login(Member inputMember) {
		Member loginMember = mapper.login(inputMember.getMemberEmail());
		
		if(loginMember == null) {
			return null;
		}
		
		if(!bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
			return null;
		}
		
		loginMember.setMemberPw(null);
		
		return loginMember;
	}
	
	@Override
	public int findId(Member member) {
		int memberCount = mapper.memberCount(member);
		
		return memberCount;
	}
	
	@Override
	public String findMemberId(Member member) {
		String memberEmail = mapper.findMemberId(member);
		
		return memberEmail;
	}
	
	@Override
	public int memberPwCount(Member member) {
		int memberNo = mapper.memberPwCount(member);
		
		return memberNo;
	}
	
	@Override
	public int changePw(String newPassword, String memberEmail) {
		
		String encPw = bcrypt.encode(newPassword);

		Map<String, Object> map = new HashMap<>();
		map.put("memberEmail", memberEmail);
		map.put("encPw", encPw);
		
		int result = mapper.changePw(map);
		
		return result;
	}
}

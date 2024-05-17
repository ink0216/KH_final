package kh.em.albaya.member.model.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

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
}

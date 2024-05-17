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
	public int signup(Member member) {
		String encPw = bcrypt.encode(member.getMemberPw());
		
		member.setMemberEmail(member.getMemberEmail());
		member.setMemberPhoneNumber(member.getMemberPhoneNumber());
		member.setMemberPw(encPw);
		member.setMemberName(member.getMemberName());
		member.setMemberAddress(member.getMemberAddress());
		member.setMemberGender(member.getMemberGender());
		
		return mapper.signup(member);
	}
}

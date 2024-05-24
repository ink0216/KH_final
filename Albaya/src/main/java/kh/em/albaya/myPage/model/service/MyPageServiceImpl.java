package kh.em.albaya.myPage.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kh.em.albaya.myPage.model.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements myPageService{
	private final MyPageMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;

	@Override
	public int myPageCheckPw(int memberNo, String memberEmail, String memberPw) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberNo", memberNo);
		map.put("memberEmail", memberEmail);
		
		String encPw = mapper.findEncPw(map);
		
		if(!bcrypt.matches(memberPw, encPw)) {
			return 0;
		}
		
		return mapper.myPageCheckPw(map);
	}
	
	@Override
	public int deleteMember(int memberNo, String memberEmail) {
		
		Map<String, Object> map = new HashMap<>();

		
		return mapper.deleteMember(map);
	}
}

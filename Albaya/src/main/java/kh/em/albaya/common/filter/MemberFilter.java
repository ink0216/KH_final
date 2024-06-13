package kh.em.albaya.common.filter;

import java.io.IOException;

import org.springframework.ui.Model;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException { // 개인회원만 할 수 있는 것
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginMember") == null ) {
	        String uri = req.getRequestURI();
	        session.setAttribute("uri", uri);
	        
			resp.sendRedirect("/loginError3");
		}else {
			chain.doFilter(request, response);
		}
	}
}
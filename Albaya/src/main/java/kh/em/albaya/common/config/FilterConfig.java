package kh.em.albaya.common.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kh.em.albaya.common.filter.LoginFilter;
import kh.em.albaya.common.filter.MemberFilter;
import kh.em.albaya.common.filter.ShopFilter;

/*만들어 놓은 Filter 클래스가 언제 적용될 지 설정
 * */
@Configuration //설정용 클래스 -> 서버가 켜질 때 해당 클래스 내 모든 메서드가 실행돼서 설정이 적용된다
public class FilterConfig {
	@Bean //반환된 객체(개발자가 만들어서 주는 거다)를 Bean으로 등록하는 어노테이션
	public FilterRegistrationBean<ShopFilter> shopFilter(){ //기업회원만 할 수 있는 것
		//메서드
		//registration == 등록
		//FilterRegistrationBean : 필터를 bean으로 등록하는 객체
		FilterRegistrationBean<ShopFilter> filter
		= new FilterRegistrationBean<>();
		
		//사용할 필터 객체 추가(등록)
		filter.setFilter(new ShopFilter()); //로그인 필터를 만들어서 필터를 껴넣는다
		//로그인 필터 == 로그인이 안되어있는 경우 특정 페이지로 돌아가게 하는 필터
		
		String[] filteringURL = {"/hire/hireWrite","/hire/hireList"}; //  /myPage로 시작하는 모든 요청이 온 경우 이 필터가 동작함!!
		//이용 못하게 하는 url을 추가하고 싶으면 추가 가능하도록 배열로 함
		//수정도 로그인 돼있어야 가능하도록!! -> 로그인 안돼있으면 메인페이지로 날리기
		
		
		// Arrays.asList(filteringURL)
				// -> filteringURL 배열을 List로 변환
		filter.setUrlPatterns(Arrays.asList(filteringURL)); //필터가 동작할 URL을 세팅하기(세팅하는 값이 여러 개 될 수 있다)
		
		//필터 이름 지정
		filter.setName("ShopFilter");
		
		//필터가 여러 개인 경우 필터 순서 지정
		filter.setOrder(2);
		
		return filter; //반환된 객체가 필터를 만들어서 bean으로 등록해주면 스프링이 관리해준다
		
		
	}

	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilter() { //회원(개인,기업)만 할 수 있는 것
		FilterRegistrationBean<LoginFilter> filter = new FilterRegistrationBean<>();
		
		filter.setFilter(new LoginFilter());
		

		String[] filteringURL = {"/myPage/*"};
		
		filter.setUrlPatterns(Arrays.asList(filteringURL));
		
		filter.setName("LoginFilter");
			
		filter.setOrder(1);
		return filter;
	}
	
	@Bean
	public FilterRegistrationBean<MemberFilter> memberFilter() { //개인회원만 할 수 있는 것
		FilterRegistrationBean<MemberFilter> filter = new FilterRegistrationBean<>();
		
		filter.setFilter(new MemberFilter());
		
		
		String[] filteringURL = {"/resume/*","/hire/hireApply/*"};
		
		filter.setUrlPatterns(Arrays.asList(filteringURL));
		
		filter.setName("MemberFilter");
		
		filter.setOrder(3);
		return filter;
	}
}
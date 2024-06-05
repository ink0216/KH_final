package kh.em.albaya.websocket.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import jakarta.servlet.http.HttpSession;

/**SessionHandshakeInterceptor 클래스 : 
 * WebSocketHandler가 동작하기 전/후에
 * 연결된 클라이언트의 세션을 가로채는 동작을 작성할 클래스
 * 
 * 가로채는 시점을 언제 가로채라고 지정하는 게 아닌, Bean으로 등록해서 스프링이 알아서 동작시키게 함
 */
@Component //Bean으로 등록하는 어노테이션
public class SessionHandshakeInterceptor implements HandshakeInterceptor{

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception { //만났을 때 악수하고 그 떄 가로챔 (Handler동작 전에 수행(전처리))
		
		/*ServerHttpRequest : HttpServletRequest의 부모 인터페이스이다
		 * ServerHttpResponse : HttpServletResponse의 부모 인터페이스이다
		 * 부모의 자식들을 다 받을 수 있게 부모 인터페이스가 매개변수로 들어감 -> 추상화(구체적으로 안써놓고 대략적으로 써서 어떤 것이든지 받을 수 있게 함)
		 * 														-> 코드 길이 감소, 업캐스팅 적용
		 * attributes	: 해당 맵에 세팅된 속성(데이터)은 다음에 동작할 Handler 객체에게 전달된다 (데이터 넘겨주는 model과 같은 용도이다!)
		 * 					(HandshakeInterceptor -> Handler 방향으로 데이터 전달하는 역할임 근데 여러 데이터 보낼 수 있게 Map 형태이다)
		 * 					세션 가로챘으니까 그걸 핸들러에게 넘겨줘야 한다
		 * */
		if(request instanceof ServletServerHttpRequest) {
			//request가 참조하는 객체가 ServletServerHttpRequest가 맞는 경우(ServletServerHttpRequest로 다운캐스팅이 가능한 경우)
			// ServletServerHttpRequest는 ServerHttpRequest의 자식 객체이다
			// 상속하면 자식 객체 안에 부모가 들어있다
			//맞으면 다운캐스팅하기
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;
			
			//세션 얻어올 수 있다
			HttpSession session = servletRequest.getServletRequest().getSession();
			//웹소켓 동작을 요청한 클라이언트의 세션을 얻어옴
			//인터셉터가 클라이언트와 악수를 할 때 클라이언트의 세션을 가로챔
			
			//세 번째 매개변수 attributes
			// 가로챈 세션을 Handler에 전달할 수 있게 값 세팅하기
			attributes.put("session", session); //map이니까 put으로 세팅하면 된다
		}
		return true; //전달할거야~~
		//return 값 true로 바꿔야 한다 -> 그래야 가로채는 동작이 수행된다(반환형이 가로채기 진행 여부여서 false면 수행되지 않음)
		//true로 해야 세션 가로채서 Handler에게 전달해줄 수 있다
		//false이면 세션이 가로채지지 않는다
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) { //헤어질 때 악수하고 그떄 가로챔(Handler동작 후에 수행(후처리) -> 근데 후처리는 잘 사용하지 않는다)
		
	}
	
}

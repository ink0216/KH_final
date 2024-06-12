package kh.em.albaya.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import kh.em.albaya.websocket.handler.NotificationWebsocketHandler;
import kh.em.albaya.websocket.handler.TestWebsocketHandler;
import lombok.RequiredArgsConstructor;

@Configuration 
@EnableWebSocket 
@RequiredArgsConstructor 
public class WebsocketConfig implements WebSocketConfigurer{ 
	
	
	private final HandshakeInterceptor handshakeInterceptor;
	
	private final NotificationWebsocketHandler notificationWebsocketHandler;
	
	private final TestWebsocketHandler testWebsocketHandler;

	
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(notificationWebsocketHandler, "/notification/send") 
				.addInterceptors(handshakeInterceptor) 
				.setAllowedOriginPatterns("http://localhost/",
											"http://127.0.0.1/", 
											"http://192.168.10.15/") 
				.withSockJS();
			registry.addHandler(testWebsocketHandler, "/testSock") 
			.addInterceptors(handshakeInterceptor) 
			.setAllowedOriginPatterns("http://localhost/",
					"http://127.0.0.1/", 
					"http://192.168.10.15/") 
			.withSockJS();
		
	}	
}

package kh.em.albaya.websocket.handler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.kh.project.board.model.dto.Board;
import jakarta.servlet.http.HttpSession;
import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.member.model.dto.Member;
import kh.em.albaya.websocket.model.dto.Notification;
import kh.em.albaya.websocket.model.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationWebsocketHandler extends TextWebSocketHandler{

	private final NotificationService service;
	
    private Set<WebSocketSession> sessions  = Collections.synchronizedSet(new HashSet<WebSocketSession>());
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }
    
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	// session : 알림을 보낸 사람의 정보가 담겨있다(좋아요를 누르든 댓글을 남긴 사람)

    	// Jackson 라이브러리 : Java에서 JSON을 다룰수 있는 클래스/메서드를 제공하는 라이브러리 (Spring Boot는 기본 내장)
       
    	// ObjectMapper :
    	// - Jackson 라이브러리에서 제공하는 객체
    	// - JSON <-> DTO Object 변경 가능
    	//자바에서 JSON과 DTO를 서로 변경해주는 역할 한다
    	//JS에서는 JSON.parse, JSON.stringify 썼었는데 이 역할을 얘가 한다 (자바 버전)
        ObjectMapper objectMapper = new ObjectMapper();
        
        // TextMessage로 전달 받은 JSON 데이터를 Notification 객체로 변경
        Notification notification = objectMapper.readValue(message.getPayload(), Notification.class);
    	//message.getPayload()이거를 JSON으로 받아오는데
        //그 JSON을 Notification 객체로 만들어서 저장하겠다
        
        
        // 웹소켓 요청을 보낸 회원 정보 얻어오기
    	HttpSession currentSession =  (HttpSession)session.getAttributes().get("session");
    	Member sendMember = ((Member)currentSession.getAttribute("loginMember"));
    	//로그인한 사람의 정보 저장 (누가 보낸 거지? ->sendMember)
    	
    	// 알림 객체(notification)에 필요한 값 세팅
    	setNotification(notification, sendMember);
    	
    	
    	
    	log.info("전달 받은 내용 : {}",notification);
    	
    	
    	// 알림 내용이 없는 경우 == 자신의 게시물
    	if(notification.getNotificationContent() == null) return;
    	
    	
    	
    	// DB에 알림 데이터 삽입
    	int result = service.insertNotification(notification);
    	
    	if(result == 0) return;
    
    	// /notification/send 로 연결된 객체를 만든 클라이언트들(sessions) 중
		// 회원 번호가 받는 회원 번호와 같은 사람에게 메시지 전달
    	
    	
		for(WebSocketSession s : sessions) {
			//좋아요 누른 회원 : 10번 회원
	    	// 접속한 회원 : 1,5,10번 회원->sessions
	    	// 좋아요가 눌린 글의 작성자 : 5번회원
	    	// s에는 한 명의 정보가 다 들어있다
	    	// WebSocketSession : 웹소켓 전용 세션
			HttpSession temp = (HttpSession)s.getAttributes().get("session");
            int loginMemberNo = ((Member)temp.getAttribute("loginMember")).getMemberNo();
			
            if(loginMemberNo == notification.getReceiveMemberNo()) { //보내는 조건을 까다롭게 만듦
            	//알림 받을 회원은 5번회원인데 1번회원으로 만약 로그인한 경우는 if문 수행 안됨 다르므로!
            	// 5번 회원이 로그인한 경우는 이 if문 실행돼서 메시지가 전달된다 -> header.js notificationSock.addEventListener로 감
            	s.sendMessage(new TextMessage(objectMapper.writeValueAsString(notification)));
            }
		}
    }
    
    
    
    // afterConnectionClosed - 클라이언트와 연결이 종료되면 실행된다.
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        //log.info("{}연결끊김",session.getId());
    }
    
    
    
    
    /** 알림 종류에 따라 알림 객체에 값 추가하기
     * @param notification
     * @return
     */
    private void setNotification(Notification notification, Member sendMember) {
    	//DB에 저장되기 위한 내용을 만드는 메서드 -> 여기에서만 쓸 수 있는 private
    	//메시지 가공용
    	
    	//알림 객체 notification 얻어옴
    	
    	// 보낸 사람 받는 번호
    	//보낸 사람의 번호를 알림에 세팅
    	notification.setSendMemberNo(sendMember.getMemberNo()); 
    	
    	// 보낸 사람 프로필 이미지를 알림에 세팅
    	notification.setSendMemberProfileImg(sendMember.getProfileImg());
    	
    	
    	// 알림을 보낼 때 필요한 게시글 관련 값 조회
    	Hire hire = service.selectBoardData(notification.getPkNo());

    	
    	// *****************************************************************
    	// 로그인한 회원이 자신의 게시글을 좋아요, 댓글 작성 한 경우 -> 알림 필요 없음
		if(sendMember.getMemberNo() == board.getMemberNo()) return;
		// *****************************************************************
    	

		
    	String content = null;
    	
    	
      	switch(notification.getNotificationType()) { 
      	
      	/* ********* 게시글 좋아요 웹소켓 send 요청 시 ********* */
    	case "boardLike" : //NotificationType 필드값 가져와서 그게 boardLike인 경우
    		
    		// 알림 내용 가공
    		content = String.format("<b>%s</b>님이 <b>[%s]</b> 게시글을 좋아합니다", 
    				sendMember.getMemberNickname(), board.getBoardTitle());
    		
    		// 알림 내용 세팅
    		notification.setNotificationContent(content);
    		
    		// 알림 받을 회원 번호 세팅
    		notification.setReceiveMemberNo(board.getMemberNo());
    		break;
    		
    		
		/* ********* 댓글 등록 웹소켓 send 요청 시 ********* */	
    	case "insertComment" : //NotificationType 필드값 가져와서 그게 insertComment인 경우
    		
			// 알림 내용 가공
			content = String.format("<b>%s</b>님이 <b>[%s]</b> 게시글에 댓글을 남겼습니다", 
    				sendMember.getMemberNickname(), board.getBoardTitle());
			// 알림 내용 세팅
    		notification.setNotificationContent(content);
    		
    		// 알림 받을 회원 번호 세팅
    		notification.setReceiveMemberNo(board.getMemberNo());
    			
    		break;
    	}
    }
}
package kh.em.albaya.websocket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Notification {

	private int notificationNo;
	private String notificationContent;
	private String notificationCheck;
	private String notificationDate;
	private String notificationUrl;
	private String sendMemberProfileImg;
	private int sendMemberNo; //알림을 보낸 사람의 번호
	private int receiveMemberNo; // 알림을 받는 사람의 번호 ***이게 더 중요하다
	
	//로직 구성을 위한 필드
	private String notificationType; //타입 따라서 알림의 내용이 달라지게 코드 짰다(알림 내용을 구분하기 위한 구분값 역할이다)
	private String title; //알림 내용에 추가될 게시글의 제목이다 (~~님이 ~~~게시글을 좋아합니다 할 때의 ~~~를 저장할 곳)
	private int pkNo; //알림이 보내진 공고 번호
	//boardTitle, boardNo라고 안하고 다른 데에도 쓸려고 범용성 위해서 그냥 title,pkNo로 했다
}

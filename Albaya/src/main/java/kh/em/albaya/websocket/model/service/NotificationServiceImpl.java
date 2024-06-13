package kh.em.albaya.websocket.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.websocket.model.dto.Notification;
import kh.em.albaya.websocket.model.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

	private final NotificationMapper mapper;
	
	@Override
	public List<Notification> selectNotification(int receiveShopNo) {
		return mapper.selectNotification(receiveShopNo);
	}
	
	// 알림을 보낼 때 필요한 게시글 관련 값 조회
	@Override
	public Hire selectBoardData(int pkNo) {
		return mapper.selectBoardData(pkNo);
	}
	
	// 알림 삽입
	@Override
	public int insertNotification(Notification notification) {
		return mapper.insertNotification(notification);
	}
	
	@Override
	public void updateNotification(int notificationNo) {
		mapper.updateNotification(notificationNo);
	}
	
	
	// 읽지 않은 알림 개수 조회
	@Override
	public int notReadCheck(int shopNo) {
		return mapper.notReadCheck(shopNo);
	}

	// 알림 삭제
	@Override
	public int deleteNotification(int notificationNo, int shopNo) {
		int result = mapper.deleteNotification(notificationNo);
		
		if(result > 0) return mapper.notReadCheck(shopNo);
		return 0;
	}
}

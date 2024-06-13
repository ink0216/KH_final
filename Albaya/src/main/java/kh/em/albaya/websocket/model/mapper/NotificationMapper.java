package kh.em.albaya.websocket.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kh.em.albaya.hire.model.dto.Hire;
import kh.em.albaya.websocket.model.dto.Notification;

@Mapper
public interface NotificationMapper {

	List<Notification> selectNotification(int receiveShopNo);

	/** 알림을 보낼 때 필요한 게시글 관련 값 조회
	 * @param pkNo
	 * @return
	 */
	Hire selectBoardData(int pkNo);
	
	/**알림 INSERT
	 * @param notification
	 * @return
	 */
	int insertNotification(Notification notification);

	void updateNotification(int notificationNo);

	// 읽지 않은 알림 개수 조회
	int notReadCheck(int shopNo);

	/** 알림 삭제
	 * @param notificationNo
	 * @return 읽지 않은 알림 개수
	 */
	int deleteNotification(int notificationNo);


}

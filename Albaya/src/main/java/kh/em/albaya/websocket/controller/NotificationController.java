package kh.em.albaya.websocket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import kh.em.albaya.shop.model.dto.Shop;
import kh.em.albaya.websocket.model.dto.Notification;
import kh.em.albaya.websocket.model.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

	private final NotificationService service;

	@GetMapping("notReadCheck")
	public int notReadCheck(@SessionAttribute("loginShop") Shop loginShop) {
		return service.notReadCheck(loginShop.getShopNo());
	}
	
	@GetMapping("")
	public List<Notification> selectNotification(@SessionAttribute("loginShop") Shop loginShop) {
		int receiveShopNo = loginShop.getShopNo();
		return service.selectNotification(receiveShopNo);
	}
	
	@PutMapping("")
	public void updateNotification(@RequestBody int notificationNo) {
		service.updateNotification(notificationNo);
        log.info("Updated notification with notificationNo: {}", notificationNo);

	}
	
	@DeleteMapping("")
	public int deleteNotification(@RequestBody int notificationNo, @SessionAttribute("loginShop") Shop loginShop) {
		return service.deleteNotification(notificationNo, loginShop.getShopNo());
	}	
}

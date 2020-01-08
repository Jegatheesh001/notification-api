package com.medas.rewamp.notificationapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medas.rewamp.notificationapi.business.vo.ApiResponse;
import com.medas.rewamp.notificationapi.business.vo.NotificationParamVO;
import com.medas.rewamp.notificationapi.service.NotificationService;

/**
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 8, 2020
 *
 */
@RestController
@RequestMapping("/notification")
public class NotificationResource {

	@Autowired
	NotificationService service;
	
	@PostMapping
	public ApiResponse<Void> saveNotificationDetails(@RequestBody NotificationParamVO paramVO) {
		return service.saveNotificationDetails(paramVO);
	}
	
	@PutMapping
	public ApiResponse<Void> updateNotificationDetails(@RequestBody NotificationParamVO paramVO) {
		return service.updateNotificationDetails(paramVO);
	}
	
	@PutMapping("/{referId}")
	public ApiResponse<Void> cancelNotificationDetails(@PathVariable Integer referId, @RequestBody NotificationParamVO paramVO) {
		return service.cancelNotificationDetails(paramVO);
	}
	
}

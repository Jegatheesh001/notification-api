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
import com.medas.rewamp.notificationapi.configuration.aspects.Loggable;
import com.medas.rewamp.notificationapi.service.NotificationService;

import io.swagger.annotations.ApiOperation;

/**
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 8, 2020
 *
 */
@RestController
@RequestMapping("/v1/notification")
public class NotificationResource {

	@Autowired
	NotificationService service;
	
	@Loggable
	@PostMapping
	@ApiOperation(value = "API for saving notification details from client", notes = "Provide all the parameters")
	public ApiResponse<Void> saveNotificationDetails(@RequestBody NotificationParamVO paramVO) {
		return service.saveNotificationDetails(paramVO);
	}
	
	@Loggable
	@PutMapping
	@ApiOperation(value = "API for updating notification details from client", notes = "Provide all the parameters")
	public ApiResponse<Void> updateNotificationDetails(@RequestBody NotificationParamVO paramVO) {
		return service.updateNotificationDetails(paramVO);
	}
	
	@Loggable
	@PutMapping("/{referId}")
	@ApiOperation(value = "API for deactive notification details trigger", notes = "Provide all the parameters")
	public ApiResponse<Void> cancelNotificationDetails(@PathVariable Integer referId, @RequestBody NotificationParamVO paramVO) {
		return service.cancelNotificationDetails(paramVO);
	}
	
}

package com.medas.rewamp.notificationapi.resource;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medas.rewamp.notificationapi.business.constants.CommonConstants;
import com.medas.rewamp.notificationapi.business.vo.ApiResponse;
import com.medas.rewamp.notificationapi.business.vo.NotificationParamVO;
import com.medas.rewamp.notificationapi.configuration.aspects.Loggable;
import com.medas.rewamp.notificationapi.service.NotificationService;

import io.swagger.annotations.ApiOperation;

/**
 * Welcome Resource
 * 
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 23, 2020
 *
 */
@RestController
public class WelcomeResource {

	@Value("${app.welcomeMsg}")
	private String welcomeMsg;
	
	@Autowired
	NotificationService service;

	@Loggable
	@ApiOperation(value = "API welcome page")
	@GetMapping
	public String welcome() {
		return welcomeMsg;
	}
	
	@Loggable
	@ApiOperation(value = "To test Mail for a particular mail setup", notes = "Provide clientId, branchId and mailId")
	@GetMapping("/checkMail/{clientId}")
	public ApiResponse<Void> checkMail(@PathVariable String clientId, @RequestParam  Integer branchId, @RequestParam String mailId) {
		LocalDateTime currentTime = LocalDateTime.now();
		NotificationParamVO paramVO = new NotificationParamVO();
		paramVO.setClientId(clientId);
		paramVO.setBranchId(branchId);
		paramVO.setNotificationType(CommonConstants.EMAIL);
		paramVO.setReferId(0);
		paramVO.setReferType("testMail");
		paramVO.setNotificationId(mailId);
		paramVO.setNotificationTemplate("Test Mail");
		paramVO.setNotificationSubject("Test Subject");
		paramVO.setInstant("Y");
		paramVO.setNotificationTime(currentTime);
		paramVO.setCurrentTime(currentTime);
		return service.saveNotificationDetails(paramVO);
	}

}

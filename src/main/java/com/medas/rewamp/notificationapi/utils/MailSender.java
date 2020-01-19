package com.medas.rewamp.notificationapi.utils;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.medas.rewamp.notificationapi.business.vo.MailSetupVO;
import com.medas.rewamp.notificationapi.business.vo.NotificationVO;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility Class to send mail
 * 
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 19, 2020
 *
 */
@Slf4j
@Component
public class MailSender {

	/**
	 * This method will run in separate thread to send mail
	 * 
	 * @param mailSetup
	 * @param data
	 */
	@Async
	public void sendMail(MailSetupVO mailSetup, NotificationVO data) {
		log.info("Mail Sending in progress..");
		log.info("Properties: {}", mailSetup.getAuthProps().toString());
	}
}

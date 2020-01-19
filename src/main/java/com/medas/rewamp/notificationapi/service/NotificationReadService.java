package com.medas.rewamp.notificationapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.medas.rewamp.notificationapi.business.constants.CommonConstants;
import com.medas.rewamp.notificationapi.business.vo.MailSetupVO;
import com.medas.rewamp.notificationapi.business.vo.NotificationVO;
import com.medas.rewamp.notificationapi.business.vo.SmsVendorVO;
import com.medas.rewamp.notificationapi.persistence.NotificationDao;
import com.medas.rewamp.notificationapi.utils.MailSender;
import com.medas.rewamp.notificationapi.utils.URIConnector;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service Class to read and send notification to client
 * 
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 12, 2020
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class NotificationReadService {

	NotificationDao dao;
	
	URIConnector uriConnect;
	
	MailSender mailSender;

	@Transactional
	public void readActiveNotifications() {
		List<NotificationVO> dataList = dao.getAllActiveNotifications();
		log.info("Total Notifications: {}", dataList.size());
		for (NotificationVO data : dataList) {
			processNotificationData(data);
		}
	}

	private void processNotificationData(NotificationVO data) {
		String notificationType = data.getNotificationType();
		boolean done = false;
		// SMS Portion
		if (notificationType.equals(CommonConstants.SMS)) {
			SmsVendorVO vendor = dao.getVendorDetails(data);
			// HTTP
			if (vendor != null && vendor.getType().equals(CommonConstants.HTTP)) {
				String url = vendor.getUrl().replace("#msg#", data.getNotificationTemplate()).replace("#mobileno#", data.getNotificationId());
				log.info("SMS details: {}", data);
				uriConnect.sendHTTPRequest(url);
				done = true;
			}
		} else if(notificationType.equals(CommonConstants.EMAIL)) {
			MailSetupVO mailSetup = dao.getMailAuthenticationDetails(data);
			mailSender.sendMail(mailSetup, data);
		}
		if (done) {
			dao.updateNotificationDoneStatus(data);
		}
	}

}

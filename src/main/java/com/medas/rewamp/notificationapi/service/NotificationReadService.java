package com.medas.rewamp.notificationapi.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.medas.rewamp.notificationapi.business.constants.CommonConstants;
import com.medas.rewamp.notificationapi.business.vo.MailAttachmentVO;
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
		List<MailAttachmentVO> attachments = dao.getAllMailAttachments();
		mapMailAttachments(dataList, attachments);
		log.info("Total Notifications: {}", dataList.size());
		for (NotificationVO data : dataList) {
			processNotificationData(data);
		}
	}

	/**
	 * Mapping all attachments to notifications
	 * 
	 * @param dataList
	 * @param attachments
	 */
	private void mapMailAttachments(List<NotificationVO> dataList, List<MailAttachmentVO> attachments) {
		Map<Integer, List<MailAttachmentVO>> mapData = attachments.stream().collect(Collectors.groupingBy(MailAttachmentVO::getNotificationId));
		dataList.forEach(data -> {
			if (mapData.get(data.getDetailId()) != null) {
				data.setAttachments(mapData.get(data.getDetailId()));
			}
		});
	}

	private void processNotificationData(NotificationVO data) {
		String notificationType = data.getNotificationType();
		boolean done = false;
		// SMS Portion
		if (notificationType.equals(CommonConstants.SMS)) {
			SmsVendorVO vendor = dao.getVendorDetails(data);
			// HTTP
			if (vendor != null) {
				if(vendor.getType().equals(CommonConstants.HTTP)) {
					String url = vendor.getUrl().replace("#msg#", data.getNotificationTemplate()).replace("#mobileno#", data.getNotificationId());
					log.info("SMS details: {}", data);
					uriConnect.sendHTTPRequest(url);
					done = true;
				}
			} else {
				log.error("No SMS Setup for clientId: {}", data.getClientId());
			}
		} else if(notificationType.equals(CommonConstants.EMAIL)) {
			MailSetupVO mailSetup = dao.getMailAuthenticationDetails(data);
			if (mailSetup != null) {
				mailSender.sendMail(mailSetup, data);
				done = true;
			} else {
				log.error("No Mail Setup for clientId: {}", data.getClientId());
			}
		}
		if (done) {
			dao.updateNotificationDoneStatus(data);
		}
	}

}

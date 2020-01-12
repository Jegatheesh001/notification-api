package com.medas.rewamp.notificationapi.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.medas.rewamp.notificationapi.business.constants.CommonConstants;
import com.medas.rewamp.notificationapi.business.vo.NotificationVO;
import com.medas.rewamp.notificationapi.business.vo.SmsVendorVO;
import com.medas.rewamp.notificationapi.persistence.NotificationDao;

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

	RestTemplate restTemplate;

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
				String url = vendor.getUrl().replace("#msg#", encodeString(data.getNotificationTemplate())).replace("#mobileno#", data.getNotificationId());
				sendHTTPRequest(url);
				done = true;
			}
		}
		if (done) {
			dao.updateNotificationDoneStatus(data);
		}
	}

	/**
	 * To encode content string
	 * 
	 * @param content
	 * @return
	 */
	private CharSequence encodeString(String content) {
		try {
			return URLEncoder.encode(content, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			log.error("Error on encoding string: {}", e.getMessage());
			return content;
		}
	}

	/**
	 * Getting response from url
	 * 
	 * @param url
	 * @return
	 */
	private boolean sendHTTPRequest(String url) {
		log.info("Url: {}", url);
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			if (response.getStatusCodeValue() == 200) {
				log.info("Response: " + response.getBody());
			}
			return true;
		} catch (Exception e) {
			log.info("Error: " + e.getMessage());
			return false;
		}
	}

}

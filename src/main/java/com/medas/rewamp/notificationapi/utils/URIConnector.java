package com.medas.rewamp.notificationapi.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * URI Connector
 * 
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 19, 2020
 *
 */
@Slf4j
@Component
@AllArgsConstructor
public class URIConnector {

	RestTemplate restTemplate;

	/**
	 * Getting response from url (Method will run in separate thread)
	 * 
	 * @param url
	 */
	@Async
	public void sendHTTPRequest(String url) {
		log.info("Url: {}", url);
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			if (response.getStatusCodeValue() == 200) {
				log.info("Response: " + response.getBody());
			}
		} catch (Exception e) {
			log.error("Error on sending HTTP request: ", e);
		}
	}
	
}

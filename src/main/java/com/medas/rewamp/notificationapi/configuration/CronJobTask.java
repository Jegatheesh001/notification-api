package com.medas.rewamp.notificationapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.medas.rewamp.notificationapi.service.NotificationReadService;

import lombok.extern.slf4j.Slf4j;

/**
 * Cron Job Scheduler
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 12, 2020
 *
 */
@Slf4j
@Component
public class CronJobTask {
	
	@Autowired
	private NotificationReadService notificationService;
	
	/**
	 * Every minute
	 */
	@Scheduled(cron = "0 * * * * *")
	public void checkActiveNotification() {
		log.info("Notification Push Scheduler Running");
		notificationService.readActiveNotifications();
		log.info("Scheduler Run Completed");
	}
}

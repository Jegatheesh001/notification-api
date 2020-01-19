package com.medas.rewamp.notificationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 8, 2020
 *
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class NotificationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApiApplication.class, args);
	}

}

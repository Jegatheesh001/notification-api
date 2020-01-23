package com.medas.rewamp.notificationapi.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medas.rewamp.notificationapi.configuration.aspects.Loggable;

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

	@Loggable
	@GetMapping
	public String welcome() {
		return welcomeMsg;
	}

}

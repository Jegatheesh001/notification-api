package com.medas.rewamp.notificationapi.business.vo;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO object to fetch data from query
 * 
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 19, 2020
 *
 */
@Data
@NoArgsConstructor
public class MailSetupVO {
	private String authMail;
	private String authPassword;
	private String authProperties;
	private JsonElement authProps;
	
	/**
	 * For Query 'getMailAuthenticationDetails' in NotificationDaoImpl
	 * 
	 * @param authMail
	 * @param authPassword
	 * @param authProperties
	 */
	public MailSetupVO(String authMail, String authPassword, String authProperties) {
		this.authMail = authMail;
		this.authPassword = authPassword;
		this.authProperties = authProperties;
		if (authProperties != null) {
			this.authProps = JsonParser.parseString(authProperties);
		}
	}
	
}

package com.medas.rewamp.notificationapi.business.vo;

import lombok.Data;

/**
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 12, 2020
 *
 */
@Data
public class SmsVendorVO {
	private String type;
	private String url;
	
	/**
	 * For query getVendorDetails in NotificationDaoImpl
	 * 
	 * @param url
	 * @param type
	 */
	public SmsVendorVO(String type, String url) {
		this.url = url;
		this.type = type;
	}
}

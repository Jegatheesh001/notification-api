package com.medas.rewamp.notificationapi.business.vo;

import lombok.Data;

/**
 * SMS Vendors
 * 
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 12, 2020
 *
 */
@Data
public class SmsVendorVO {
	/**
	 * SMS API type
	 */
	private String type;
	/**
	 * SMS uri path
	 */
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

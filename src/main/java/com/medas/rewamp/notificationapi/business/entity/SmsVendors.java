package com.medas.rewamp.notificationapi.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 12, 2020
 *
 */
@Entity
@Data
@Table(name = "sms_vendors")
public class SmsVendors {

	/**
	 * Primary Key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vendorId;
	
	@Column(nullable = false)
	private String vendorName;
	
	/**
	 * API type eg. http/basic
	 */
	@Column(nullable = false)
	private String apiType;
	
	/**
	 * API url
	 */
	@Column(nullable = false)
	private String url;
	
	/**
	 * Client Id
	 */
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private ClientDetails client;
	
	/**
	 * Branch Id of client
	 */
	@Column(nullable = false)
	private Integer branchId;
}

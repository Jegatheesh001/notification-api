package com.medas.rewamp.notificationapi.business.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * To capture client's mail authentication details
 * 
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 19, 2020
 *
 */
@Entity
@Data
@Table(name = "mail_setup")
public class MailSetup {
	/**
	 * Primary Key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mailSetupId;
	/**
	 * Client Id
	 */
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private ClientDetails client;
	
	/**
	 * Branch Id of client
	 */
	private Integer branchId;
	
	/**
	 * Authentication mail
	 */
	private String authMail;
	
	/**
	 * Authentication password
	 */
	private String authPassword;
	
	/**
	 * Authentication properties eg. port, protocol
	 */
	private String authProperties;
}

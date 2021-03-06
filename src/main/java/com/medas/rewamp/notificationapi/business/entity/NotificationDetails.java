package com.medas.rewamp.notificationapi.business.entity;

import java.time.LocalDateTime;

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
 * Entity mapping for notification_details table
 * 
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 8, 2020
 *
 */
@Data
@Entity
@Table(name = "notification_details")
public class NotificationDetails {

	/**
	 * Primary Key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer detailId;
	
	/**
	 * Notification reference type (to distinguish notification of multiple module). eg app/reg
	 */
	@Column(nullable = false)
	private String referType;
	
	/**
	 * Reference Id for Reference type
	 */
	@Column(nullable = false)
	private Integer referId;
	
	/**
	 * Type of Notification eg. sms/email
	 */
	@Column(nullable = false)
	private String notificationType;
	
	/**
	 * Notification subject
	 */
	@Column(nullable = true)
	private String notificationSubject;
	
	/**
	 * Notification Id eg. Mobile No/ E-mail id
	 */
	@Column(nullable = false)
	private String notificationId;
	
	/**
	 * Notification Message
	 */
	@Column(columnDefinition = "TEXT", nullable = false)
	private String notificationTemplate;
	
	/**
	 * Notification Time (Time which notification needs to send)
	 */
	@Column(nullable = false)
	private LocalDateTime notificationTime;
	
	/**
	 * Notified Time (Time which notification send)
	 */
	@Column(nullable = true)
	private LocalDateTime doneTime;
	
	/**
	 * Instant Message Status
	 */
	@Column(nullable = false)
	private String instant;
	
	/**
	 * Notification Active status
	 */
	@Column(nullable = false)
	private String activeStatus;
	
	/**
	 * Client Id
	 */
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private ClientDetails clientDetails;
	
	/**
	 * Branch Id of client
	 */
	@Column(nullable = false)
	private Integer branchId;
	
	/**
	 * Constructors starts here
	 *
	 */
	
	public NotificationDetails(Integer detailId) {
		super();
		this.detailId = detailId;
	}

	/**
	 * Inserting data to NotificationDetails table
	 * 
	 * @param referType
	 * @param referId
	 * @param notificationType
	 * @param notificationId
	 * @param notificationTemplate
	 * @param instant
	 * @param notificationTime
	 * @param clientId
	 * @param branchId
	 */
	public NotificationDetails(String referType, Integer referId, String notificationType, String notificationId,
			String notificationTemplate, String notificationSubject, String instant, LocalDateTime notificationTime, Integer clientId, Integer branchId) {
		super();
		this.referType = referType;
		this.referId = referId;
		this.notificationType = notificationType;
		this.notificationId = notificationId;
		this.notificationTemplate = notificationTemplate;
		this.notificationSubject = notificationSubject;
		this.instant = instant;
		this.notificationTime = notificationTime;
		this.clientDetails = new ClientDetails(clientId);
		this.branchId = branchId;
		this.activeStatus = "Y";
	}
	
}

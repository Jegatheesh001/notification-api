package com.medas.rewamp.notificationapi.business.entity;

import java.time.LocalDateTime;

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
		   <b>Created</b> On Jan 8, 2020
 *
 */
@Data
@Entity
@Table(name = "Notification_Details")
public class NotificationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer detailId;
	
	private String referType;
	
	private Integer referId;
	
	private String notificationType;
	
	private String notificationId;
	
	private String notificationTemplate;
	
	private LocalDateTime notificationTime;
	
	private LocalDateTime doneTime;
	
	private String activeStatus;
	
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private ClientDetails clientDetails;
	
	private Integer branchId;

	/**
	 * Inserting data
	 * 
	 * @param referType
	 * @param referId
	 * @param notificationType
	 * @param notificationId
	 * @param notificationTemplate
	 * @param notificationTime
	 * @param clientId
	 * @param branchId
	 */
	public NotificationDetails(String referType, Integer referId, String notificationType, String notificationId,
			String notificationTemplate, LocalDateTime notificationTime, Integer clientId, Integer branchId) {
		super();
		this.referType = referType;
		this.referId = referId;
		this.notificationType = notificationType;
		this.notificationId = notificationId;
		this.notificationTemplate = notificationTemplate;
		this.notificationTime = notificationTime;
		this.clientDetails = new ClientDetails(clientId);
		this.branchId = branchId;
		this.activeStatus = "Y";
	}
	
}

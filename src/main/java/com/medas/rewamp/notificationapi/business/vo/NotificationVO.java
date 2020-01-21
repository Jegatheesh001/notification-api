package com.medas.rewamp.notificationapi.business.vo;

import java.util.List;

import lombok.Data;

/**
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 12, 2020
 *
 */
@Data
public class NotificationVO {
	private Integer detailId;
	private String notificationType;
	private String notificationSubject;
	private String notificationId;
	private String notificationTemplate;
	private Integer clientId;
	private Integer branchId;
	private List<MailAttachmentVO> attachments;
	
	/**
	 * For Query getAllActiveNotifications in NotificationDaoImpl
	 * 
	 * @param detailId
	 * @param notificationType
	 * @param notificationId
	 * @param notificationSubject
	 * @param notificationTemplate
	 * @param clientId
	 * @param branchId
	 */
	public NotificationVO(Integer detailId, String notificationType, String notificationSubject, String notificationId, String notificationTemplate, Integer clientId,
			Integer branchId) {
		super();
		this.detailId = detailId;
		this.notificationType = notificationType;
		this.notificationSubject = notificationSubject;
		this.notificationId = notificationId;
		this.notificationTemplate = notificationTemplate;
		this.clientId = clientId;
		this.branchId = branchId;
	}
	
}

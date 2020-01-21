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
 * For mail Attachment table
 * 
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 21, 2020
 *
 */
@Data
@Entity
@Table(name = "mail_attachment")
public class MailAttachment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attachmentId;
	@ManyToOne
	@JoinColumn(name = "notification_id", nullable = false)
	private NotificationDetails notification;
	private String attachmentName;
	/**
	 * Attachment Type eg.inline/attachment
	 */
	private String attachmentType;
	/**
	 * Attachment (base64 content)
	 */
	private String attachment;
	
	private String fileExtension;
}

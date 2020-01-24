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
	@Column(nullable = false)
	private String attachmentName;
	/**
	 * Attachment Type eg.inline/attachment
	 */
	@Column(nullable = false)
	private String attachmentType;
	/**
	 * Attachment (base64 content)
	 */
	@Column(columnDefinition = "TEXT", nullable = false)
	private String attachment;
	
	@Column(nullable = false)
	private String fileExtension;

	/**
	 * Constructors starts here
	 *
	 */
	public MailAttachment(Integer notificationId, String attachmentName, String attachmentType,
			String attachment, String fileExtension) {
		super();
		this.notification = new NotificationDetails(notificationId);
		this.attachmentName = attachmentName;
		this.attachmentType = attachmentType;
		this.attachment = attachment;
		this.fileExtension = fileExtension;
	}
}

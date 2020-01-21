package com.medas.rewamp.notificationapi.business.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 21, 2020
 *
 */
@Data
@NoArgsConstructor
public class MailAttachmentVO {
	private Integer notificationId;
	private String attachmentName;
	private String attachmentType;
	private String attachment;
	private String fileExtension;
	
	public MailAttachmentVO(Integer notificationId) {
		super();
		this.notificationId = notificationId;
	}
	
	/**
	 * For Query getAllMailAttachments in NotificationDaoImpl
	 * 
	 * @param notificationId
	 * @param attachmentName
	 * @param attachmentType
	 * @param attachment
	 * @param fileExtension
	 */
	public MailAttachmentVO(Integer notificationId, String attachmentName, String attachmentType, String attachment,
			String fileExtension) {
		super();
		this.notificationId = notificationId;
		this.attachmentName = attachmentName;
		this.attachmentType = attachmentType;
		this.attachment = attachment;
		this.fileExtension = fileExtension;
	}
	
}

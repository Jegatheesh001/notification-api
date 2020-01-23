package com.medas.rewamp.notificationapi.business.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Mail Attachments Request Parameters
 * 
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 23, 2020
 *
 */
@Data
@NoArgsConstructor
public class NotificationAttachmentVO {
	private String attachmentType;
	private String attachmentName;
	private String attachment;
	private String fileExtension;
}

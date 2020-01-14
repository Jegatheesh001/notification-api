package com.medas.rewamp.notificationapi.business.vo;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 8, 2020
 *
 */
@Data
@ApiModel(description = "Notification Parameters")
public class NotificationParamVO {
	@ApiModelProperty(notes = "Notification reference type (to distinguish notification of multiple module). eg app/reg")
	private String referType;
	@ApiModelProperty(notes = "Reference Id for Reference type")
	private Integer referId;
	@ApiModelProperty(notes = "Type of Notification eg. sms/email")
	private String notificationType;
	@ApiModelProperty(notes = "Notification Id eg. Mobile No/ E-mail id")
	private String notificationId;
	@ApiModelProperty(notes = "Notification Message")
	private String notificationTemplate;
	@ApiModelProperty(notes = "Instant Message Status")
	private String instant;
	@ApiModelProperty(notes = "Notification Time (Time which notification needs to send)")
	private LocalDateTime notificationTime;
	@ApiModelProperty(notes = "Current Time")
	private LocalDateTime currentTime;
	@ApiModelProperty(notes = "Client Unique Identification Id")
	private String clientId;
	@ApiModelProperty(notes = "Branch Id")
	private Integer branchId;
}

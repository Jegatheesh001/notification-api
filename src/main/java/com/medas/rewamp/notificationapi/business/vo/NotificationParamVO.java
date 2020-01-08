package com.medas.rewamp.notificationapi.business.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NotificationParamVO {
	private String referType;
	private Integer referId;
	private String notificationType;
	private String notificationId;
	private String notificationTemplate;
	private LocalDateTime notificationTime;
	private LocalDateTime currentTime;
	private String clientId;
	private Integer branchId;
}

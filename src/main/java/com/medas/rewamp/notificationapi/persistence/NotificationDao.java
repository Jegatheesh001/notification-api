package com.medas.rewamp.notificationapi.persistence;

import com.medas.rewamp.notificationapi.business.vo.NotificationParamVO;

public interface NotificationDao {

	public Integer validateClient(NotificationParamVO paramVO);

	public void updateNotificationDetails(NotificationParamVO paramVO, Integer clientId);

	public void cancelNotificationDetails(NotificationParamVO paramVO, Integer clientId);

}

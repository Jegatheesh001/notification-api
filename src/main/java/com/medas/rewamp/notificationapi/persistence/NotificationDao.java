package com.medas.rewamp.notificationapi.persistence;

import java.util.List;

import com.medas.rewamp.notificationapi.business.vo.NotificationParamVO;
import com.medas.rewamp.notificationapi.business.vo.NotificationVO;
import com.medas.rewamp.notificationapi.business.vo.SmsVendorVO;

public interface NotificationDao {

	public Integer validateClient(NotificationParamVO paramVO);

	public void updateNotificationDetails(NotificationParamVO paramVO, Integer clientId);

	public void cancelNotificationDetails(NotificationParamVO paramVO, Integer clientId);

	public List<NotificationVO> getAllActiveNotifications();

	public void updateNotificationDoneStatus(NotificationVO paramVO);

	public SmsVendorVO getVendorDetails(NotificationVO data);
	
}

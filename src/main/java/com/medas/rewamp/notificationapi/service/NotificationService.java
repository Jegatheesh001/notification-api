package com.medas.rewamp.notificationapi.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.medas.rewamp.notificationapi.business.constants.CommonConstants;
import com.medas.rewamp.notificationapi.business.entity.NotificationDetails;
import com.medas.rewamp.notificationapi.business.vo.ApiResponse;
import com.medas.rewamp.notificationapi.business.vo.NotificationParamVO;
import com.medas.rewamp.notificationapi.persistence.NotificationDao;
import com.medas.rewamp.notificationapi.persistence.repository.NotificationDetailsRepository;

import lombok.AllArgsConstructor;

/**
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 8, 2020
 *
 */
@Service
@AllArgsConstructor
public class NotificationService {
	
	NotificationDao dao;
	NotificationDetailsRepository repository;

	public ApiResponse<Void> saveNotificationDetails(NotificationParamVO paramVO) {
		ApiResponse<Void> response = null;
		Integer clientId = dao.validateClient(paramVO);
		if (clientId != null) {
			NotificationDetails entity = new NotificationDetails(paramVO.getReferType(), paramVO.getReferId(),
					paramVO.getNotificationType(), paramVO.getNotificationId(), paramVO.getNotificationTemplate(),
					paramVO.getInstant(), paramVO.getNotificationTime(), clientId, paramVO.getBranchId());
			repository.save(entity);
			response = new ApiResponse<>(true);
		} else {
			response = new ApiResponse<>(false, CommonConstants.INVALID_CLIENT);
		}
		return response;
	}

	public ApiResponse<Void> updateNotificationDetails(NotificationParamVO paramVO) {
		ApiResponse<Void> response = null;
		Integer clientId = dao.validateClient(paramVO);
		if (clientId != null) {
			dao.updateNotificationDetails(paramVO, clientId);
			response = new ApiResponse<>(true);
		} else {
			response = new ApiResponse<>(false, CommonConstants.INVALID_CLIENT);
		}
		return response;
	}

	@Transactional
	public ApiResponse<Void> cancelNotificationDetails(NotificationParamVO paramVO) {
		ApiResponse<Void> response = null;
		Integer clientId = dao.validateClient(paramVO);
		if (clientId != null) {
			dao.cancelNotificationDetails(paramVO, clientId);
			response = new ApiResponse<>(true);
		} else {
			response = new ApiResponse<>(false, CommonConstants.INVALID_CLIENT);
		}
		return response;
	}

}

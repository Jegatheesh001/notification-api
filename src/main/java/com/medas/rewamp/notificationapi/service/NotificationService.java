package com.medas.rewamp.notificationapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.medas.rewamp.notificationapi.business.constants.CommonConstants;
import com.medas.rewamp.notificationapi.business.entity.MailAttachment;
import com.medas.rewamp.notificationapi.business.entity.NotificationDetails;
import com.medas.rewamp.notificationapi.business.vo.ApiResponse;
import com.medas.rewamp.notificationapi.business.vo.NotificationAttachmentVO;
import com.medas.rewamp.notificationapi.business.vo.NotificationParamVO;
import com.medas.rewamp.notificationapi.persistence.NotificationDao;
import com.medas.rewamp.notificationapi.persistence.repository.MailAttachmentRepository;
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
	MailAttachmentRepository attachmentRepo;

	@Transactional(rollbackOn = Exception.class)
	public ApiResponse<Void> saveNotificationDetails(NotificationParamVO paramVO) {
		ApiResponse<Void> response = null;
		Integer clientId = dao.validateClient(paramVO);
		if (clientId != null) {
			NotificationDetails entity = new NotificationDetails(paramVO.getReferType(), paramVO.getReferId(),
					paramVO.getNotificationType(), paramVO.getNotificationId(), paramVO.getNotificationTemplate(),
					paramVO.getNotificationSubject(), paramVO.getInstant(), paramVO.getNotificationTime(), clientId,
					paramVO.getBranchId());
			Integer notificationId = repository.save(entity).getDetailId();
			saveNotificationAttachments(notificationId, paramVO.getAttachments());
			response = new ApiResponse<>(true);
		} else {
			response = new ApiResponse<>(false, CommonConstants.INVALID_CLIENT);
		}
		return response;
	}

	/**
	 * Method to save notification attachments
	 * 
	 * @param notificationId
	 * @param attachments
	 */
	private void saveNotificationAttachments(Integer notificationId, List<NotificationAttachmentVO> attachments) {
		if (attachments != null && !attachments.isEmpty()) {
			for (NotificationAttachmentVO attachment : attachments) {
				attachmentRepo.save(new MailAttachment(notificationId, attachment.getAttachmentName(),
						attachment.getAttachmentType(), attachment.getAttachment(), attachment.getFileExtension()));
			}
		}
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

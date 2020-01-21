package com.medas.rewamp.notificationapi.persistence.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.medas.rewamp.notificationapi.business.constants.QueryConstants;
import com.medas.rewamp.notificationapi.business.vo.MailAttachmentVO;
import com.medas.rewamp.notificationapi.business.vo.MailSetupVO;
import com.medas.rewamp.notificationapi.business.vo.NotificationParamVO;
import com.medas.rewamp.notificationapi.business.vo.NotificationVO;
import com.medas.rewamp.notificationapi.business.vo.SmsVendorVO;
import com.medas.rewamp.notificationapi.persistence.NotificationDao;

/**
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 8, 2020
 *
 */
@SuppressWarnings("unchecked")
@Repository
public class NotificationDaoImpl implements NotificationDao {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	private Session getSession() {
		return em.unwrap(Session.class);
	}

	public Integer validateClient(NotificationParamVO paramVO) {
		String queryStr = "select clientId from ClientDetails where activeStatus = 'Y' and clientUid = :clientUid ";
		try {
			return (Integer) em.createQuery(queryStr).setParameter("clientUid", paramVO.getClientId())
					.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

	@Override
	public void updateNotificationDetails(NotificationParamVO paramVO, Integer clientId) {
		String queryStr = "update NotificationDetails set notificationId = :notificationId, notificationTemplate = :notificationTemplate "
				+ "where referId = :referId and referType = :referType "
				+ "and clientDetails.clientId = :clientId and branchId = :branchId ";
		em.createQuery(queryStr).setParameter("notificationId", paramVO.getNotificationId())
				.setParameter("notificationTemplate", paramVO.getNotificationTemplate())
				.setParameter("referId", paramVO.getReferId()).setParameter("referType", paramVO.getReferType())
				.setParameter(QueryConstants.CLIENT_ID, clientId).setParameter(QueryConstants.BRANCH_ID, paramVO.getBranchId())
				.executeUpdate();
	}

	@Override
	public void cancelNotificationDetails(NotificationParamVO paramVO, Integer clientId) {
		String queryStr = "update NotificationDetails set activeStatus = 'N' "
				+ "where activeStatus = 'Y' and instant = 'N' and referId = :referId and referType = :referType "
				+ "and clientDetails.clientId = :clientId and branchId = :branchId ";
		em.createQuery(queryStr).setParameter("referId", paramVO.getReferId())
				.setParameter("referType", paramVO.getReferType()).setParameter(QueryConstants.CLIENT_ID, clientId)
				.setParameter(QueryConstants.BRANCH_ID, paramVO.getBranchId()).executeUpdate();
	}

	@Override
	public List<NotificationVO> getAllActiveNotifications() {
		LocalDateTime currentTime = LocalDateTime.now();
		String queryStr = "select new com.medas.rewamp.notificationapi.business.vo.NotificationVO(detailId, notificationType, "
				+ "notificationSubject, notificationId, notificationTemplate, clientDetails.clientId, branchId) "
				+ "from NotificationDetails where activeStatus = 'Y' "
				+ "and notificationTime>= :startTime and notificationTime<= :notificationTime";
		return em.createQuery(queryStr).setParameter("startTime", currentTime.minusDays(1))
				.setParameter("notificationTime", currentTime).getResultList();
	}
	
	@Override
	public List<MailAttachmentVO> getAllMailAttachments() {
		LocalDateTime currentTime = LocalDateTime.now();
		String queryStr = "select new com.medas.rewamp.notificationapi.business.vo.MailAttachmentVO(nd.detailId, "
				+ "ma.attachmentName, ma.attachmentType, ma.attachment, ma.fileExtension) "
				+ "from MailAttachment ma inner join ma.notification nd "
				+ "where nd.activeStatus = 'Y' and nd.notificationTime>= :startTime and nd.notificationTime<= :notificationTime";
		return em.createQuery(queryStr).setParameter("startTime", currentTime.minusDays(1))
				.setParameter("notificationTime", currentTime).getResultList();
	}
	
	@Override
	public void updateNotificationDoneStatus(NotificationVO paramVO) {
		String queryStr = "update NotificationDetails set activeStatus = 'N', doneTime = :doneTime "
				+ "where detailId = :detailId ";
		em.createQuery(queryStr).setParameter("detailId", paramVO.getDetailId())
				.setParameter("doneTime", LocalDateTime.now()).executeUpdate();
	}

	@Override
	public SmsVendorVO getVendorDetails(NotificationVO data) {
		String queryStr = "select new com.medas.rewamp.notificationapi.business.vo.SmsVendorVO(apiType, url) "
				+ "from SmsVendors where client.clientId = :clientId and branchId = :branchId";
		try {
			return (SmsVendorVO) em.createQuery(queryStr).setParameter(QueryConstants.CLIENT_ID, data.getClientId())
					.setParameter(QueryConstants.BRANCH_ID, data.getBranchId()).getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

	@Override
	public MailSetupVO getMailAuthenticationDetails(NotificationVO data) {
		String queryStr = "select new com.medas.rewamp.notificationapi.business.vo.MailSetupVO(authMail, authPassword, authProperties) "
				+ "from MailSetup where client.clientId = :clientId and branchId = :branchId";
		try {
			return (MailSetupVO) em.createQuery(queryStr).setParameter(QueryConstants.CLIENT_ID, data.getClientId())
					.setParameter(QueryConstants.BRANCH_ID, data.getBranchId()).getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

}

package com.medas.rewamp.notificationapi.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.medas.rewamp.notificationapi.business.vo.NotificationParamVO;
import com.medas.rewamp.notificationapi.persistence.NotificationDao;

/**
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 8, 2020
 *
 */
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
				.setParameter("clientId", clientId).setParameter("branchId", paramVO.getBranchId())
				.executeUpdate();
	}

	@Override
	public void cancelNotificationDetails(NotificationParamVO paramVO, Integer clientId) {
		String queryStr = "update NotificationDetails set activeStatus = 'N' "
				+ "where activeStatus = 'Y' and referId = :referId and referType = :referType "
				+ "and clientDetails.clientId = :clientId and branchId = :branchId ";
		em.createQuery(queryStr).setParameter("referId", paramVO.getReferId())
				.setParameter("referType", paramVO.getReferType()).setParameter("clientId", clientId)
				.setParameter("branchId", paramVO.getBranchId()).executeUpdate();
	}

}

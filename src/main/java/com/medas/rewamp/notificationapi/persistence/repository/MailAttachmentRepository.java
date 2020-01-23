package com.medas.rewamp.notificationapi.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.medas.rewamp.notificationapi.business.entity.MailAttachment;

/**
 * MailAttachment repository
 * 
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 23, 2020
 *
 */
public interface MailAttachmentRepository extends CrudRepository<MailAttachment, Integer> {

}

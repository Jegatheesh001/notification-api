package com.medas.rewamp.notificationapi.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.medas.rewamp.notificationapi.business.entity.NotificationDetails;

public interface NotificationDetailsRepository extends CrudRepository<NotificationDetails, Integer> {

}

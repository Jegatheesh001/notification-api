package com.medas.rewamp.notificationapi.business.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 8, 2020
 *
 */
@Data
@Entity
@Table(name = "client_details")
public class ClientDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clientId;
	
	private String clientUid;
	
	private String clientName;
	
	private String activeStatus;

	public ClientDetails(Integer clientId) {
		this.clientId = clientId;
	}
	
}

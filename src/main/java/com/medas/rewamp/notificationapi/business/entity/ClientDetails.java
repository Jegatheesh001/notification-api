package com.medas.rewamp.notificationapi.business.entity;

import javax.persistence.Column;
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
	/**
	 * Unique client Identification Id
	 */
	@Column(nullable = false)
	private String clientUid;
	@Column(nullable = false)
	private String clientName;
	@Column(nullable = false)
	private String activeStatus;

	public ClientDetails(Integer clientId) {
		this.clientId = clientId;
	}
	
}

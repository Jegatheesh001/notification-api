package com.medas.rewamp.notificationapi.business.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
	private boolean success;
	private String message;
	private T data;
	
	public ApiResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public ApiResponse(T data) {
		super();
		this.data = data;
		this.success = true;
	}

	public ApiResponse(boolean success) {
		super();
		this.success = success;
	}
}

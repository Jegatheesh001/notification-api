package com.medas.rewamp.notificationapi.business.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 8, 2020
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
@ApiModel(description = "API Response object")
public class ApiResponse<T> {
	
	@ApiModelProperty(notes = "Response Status")
	private boolean success;
	@ApiModelProperty(notes = "Error message, if any.")
	private String message;
	@ApiModelProperty(notes = "Response Data")
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

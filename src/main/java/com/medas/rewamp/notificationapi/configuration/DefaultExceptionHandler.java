package com.medas.rewamp.notificationapi.configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.medas.rewamp.notificationapi.business.vo.ApiResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * To Handle Exception in the Context
 * 
 * @author Jegatheesh <br>
 *         <b>Created</b> On Jan 11, 2020
 *
 */
@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * It will Handle all Other Unhandled Exceptions.<br>
	 * It will log the Error into the system
	 * 
	 */
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		ApiResponse<Void> error = new ApiResponse<> (false, ex.getMessage());
		log.error("Unknown Error: ", ex);
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.OK, request);
	}
}

package com.ibm.airlineScheduler.masterapicontroller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ibm.airlineScheduler.masterapimodel.CustomException;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {
		@ExceptionHandler(Exception.class)

	  public final ResponseEntity<Object> handleAllExceptions(Exception ex) {

	    CustomException exceptionResponse =

	        new CustomException();
	    exceptionResponse.setExceptionMsg(ex.getMessage());
	    return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	  }
}

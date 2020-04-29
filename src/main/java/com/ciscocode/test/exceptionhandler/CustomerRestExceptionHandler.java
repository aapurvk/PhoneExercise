package com.ciscocode.test.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ciscocode.test.controller.UserPhoneController;

@ControllerAdvice
public class CustomerRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CustomerRestExceptionHandler.class);

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(NotFoundException exc) {
		
		LOGGER.error(getClass()+ ">> Error Message - " + exc.getMessage());
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception exc, WebRequest request) {
		
        List<String> details = new ArrayList<>();
        details.add(exc.getLocalizedMessage());
        LOGGER.error(getClass()+ ">> Error Message - " + exc.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), details.get(0));
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exc, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : exc.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        LOGGER.error(getClass()+ ">> Error Message - " + exc.getMessage());
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), details.get(0));
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}

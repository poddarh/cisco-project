package assignment.cisco.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import assignment.cisco.model.ErrorModel;
import assignment.cisco.util.exception.RestException;

@ControllerAdvice
public class GenericExceptionHandler{
	private static final Logger log = LoggerFactory.getLogger(GenericExceptionHandler.class);
	
	@ResponseBody
	@ExceptionHandler({Exception.class, RuntimeException.class})
	public ErrorModel handleAllExceptions(Exception e, HttpServletRequest request, HttpServletResponse response) {
		ErrorModel em = getErrorModel(e, request);
		
		HttpStatus status = null;
		if(e instanceof RestException)
			status = ((RestException)e).getHttpStatus();
		else
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		response.setStatus(status.value());
		
		log.info(em.getMessage());
		return em;
	}
	
	private ErrorModel getErrorModel(Exception e, HttpServletRequest request) {
		ErrorModel errorModel = new ErrorModel(e.getMessage());
		errorModel.setUrl(request.getRequestURL().toString());
		errorModel.setVerb(request.getMethod());
		return errorModel;
	}
	
}
package cisco.assignment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cisco.assignment.exception.RestException;
import cisco.assignment.model.ErrorModel;

@ControllerAdvice
public class GenericExceptionHandler{
	private static final Logger log = LoggerFactory.getLogger(GenericExceptionHandler.class);
	
	/**
	 * 
	 * This method handles all kinds of exceptions thrown by any of the controllers.
	 * 
	 * @param e the exception thrown
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return an ErrorModel object which is to be serialized to JSON and returned.
	 */
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ErrorModel handleAllExceptions(Exception e, HttpServletRequest request, HttpServletResponse response) {
		ErrorModel em = getErrorModel(e, request);
		
		HttpStatus status = null;
		if(e instanceof RestException){
			status = ((RestException)e).getHttpStatus();
			em.setMessage(e.getMessage());
			log.warn(status.value() + ": " + em.getMessage());
		}
		else if(e instanceof HttpMessageNotReadableException){
			status = HttpStatus.BAD_REQUEST;
			em.setMessage("Invalid JSON sent");
			log.warn(status.value() + ": " + em.getMessage());
		}
		else{
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			em.setMessage("There was an Internal Server Error.");
			log.error(em.getMessage(), em);
		}
		
		response.setStatus(status.value());
		return em;
	}
	
	private ErrorModel getErrorModel(Exception e, HttpServletRequest request) {
		ErrorModel errorModel = new ErrorModel();
		errorModel.setUrl(request.getRequestURL().toString());
		errorModel.setVerb(request.getMethod());
		return errorModel;
	}
	
}
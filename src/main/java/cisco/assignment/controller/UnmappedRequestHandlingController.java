package cisco.assignment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cisco.assignment.exception.RestException;

@RestController
@RequestMapping(produces="application/json")
public class UnmappedRequestHandlingController {
	
	/**
	 * This method is used to capture all unmapped requests and throw an exception that will be caught by
	 * the {@link GenericExceptionHandler}
	 */
	@RequestMapping("/**")
	public void handleRequestsNotSupported() {
		throw new RestException("Invalid endpoint", HttpStatus.NOT_FOUND);
	}
}

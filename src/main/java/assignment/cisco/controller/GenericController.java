package assignment.cisco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assignment.cisco.util.exception.RestException;

@RestController
@RequestMapping(produces="application/json")
public class GenericController {
	
	@RequestMapping("/**")
	public void handleRequestsNotSupported() {
		throw new RestException("Invalid endpoint or method not supported", HttpStatus.NOT_FOUND);
	}
}

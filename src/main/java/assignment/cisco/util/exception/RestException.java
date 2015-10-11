package assignment.cisco.util.exception;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;
	
	public RestException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public final HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
}

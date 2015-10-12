package cisco.assignment.exception;

import org.springframework.http.HttpStatus;

/**
 * A generic {@link RuntimeException} that maintains a {@link HttpStatus} data which is 
 * sent to the user in case this exception type is thrown.
 * @author Harsh Poddar
 *
 */
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

package cisco.assignment.exception;

import org.springframework.http.HttpStatus;

/**
 * A {@link RestException} thrown if the user's request contains invalid data
 * @author Harsh Poddar
 *
 */
public class BadRequestException extends RestException {
	private static final long serialVersionUID = 1L;
	
	public BadRequestException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
	
}

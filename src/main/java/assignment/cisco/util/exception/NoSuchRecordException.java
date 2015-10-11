package assignment.cisco.util.exception;

import org.springframework.http.HttpStatus;

public class NoSuchRecordException extends RestException {
	private static final long serialVersionUID = 1L;
	
	public NoSuchRecordException() {
		super("No record found for the given uid", HttpStatus.BAD_REQUEST);
	}
	
}

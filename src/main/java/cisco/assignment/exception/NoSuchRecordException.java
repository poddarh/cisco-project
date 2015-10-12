package cisco.assignment.exception;

import org.springframework.http.HttpStatus;

/**
 * A {@link RestException} thrown if the record that the user is trying to access or perform on does not exist
 * @author Harsh Poddar
 *
 */
public class NoSuchRecordException extends RestException {
	private static final long serialVersionUID = 1L;
	
	public NoSuchRecordException() {
		super("No record found for the given uid", HttpStatus.BAD_REQUEST);
	}
	
}

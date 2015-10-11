package cisco.assignment.model;

public class ErrorModel {
	private String verb;
	private String url;
	private String message;
	
	public ErrorModel(String message) {
		this.message = message;
	}

	public ErrorModel(String verb, String url, String message) {
		this.verb = verb;
		this.url = url;
		this.message = message;
	}
	
	public final String getVerb() {
		return verb;
	}
	public final void setVerb(String verb) {
		this.verb = verb;
	}
	public final String getUrl() {
		return url;
	}
	public final void setUrl(String url) {
		this.url = url;
	}
	public final String getMessage() {
		return message;
	}
	public final void setMessage(String message) {
		this.message = message;
	}
}

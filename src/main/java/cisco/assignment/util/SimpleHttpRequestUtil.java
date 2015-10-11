package cisco.assignment.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SimpleHttpRequestUtil implements HttpRequestUtil {

	@Override
	public String getBaseURL() {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String requestURL = request.getRequestURL().toString();
		String requestURI = request.getRequestURI();
		
		return requestURL.substring(0, requestURL.length() - requestURI.length());
	}
	
}

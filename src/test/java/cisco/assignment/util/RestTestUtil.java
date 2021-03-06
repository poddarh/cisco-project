package cisco.assignment.util;

import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

public class RestTestUtil {
	
	private static final RestTemplate REST_TEMPLATE;
	
	static {
		REST_TEMPLATE = new TestRestTemplate();
	}
	
	public static <T, D> T request (String url, D data, HttpMethod method, Class<T> clazz, Object... uriVariables) throws JsonProcessingException {
		HttpEntity<String> entity = generateHttpEntity(data);
		ResponseEntity<T> exchange = REST_TEMPLATE.exchange(url, method, entity, clazz, uriVariables);
		T response = exchange.getBody();
		return response;
	}
	
	public static <D> HttpEntity<String> generateHttpEntity(D data) throws JsonProcessingException {
		HttpHeaders requestHeaders = getBasicHeaders();
		String jsonData = JsonUtil.toJson(data);
		HttpEntity<String> entity = new HttpEntity<String>(jsonData, requestHeaders);
		return entity;
	}
	
	public static HttpHeaders getBasicHeaders() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		return requestHeaders;
	}
	
}

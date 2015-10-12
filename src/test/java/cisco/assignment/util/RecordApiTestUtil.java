package cisco.assignment.util;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class RecordApiTestUtil {
	
	private String ENDPOINT_URL;
	private String UID_ENDPOINT_URL;
	
	public void configure(int port, String endpointURI) {
		ENDPOINT_URL = "http://localhost:"+port+endpointURI;
		UID_ENDPOINT_URL = ENDPOINT_URL+"/{uid}";
	}
	
	public <T, D> T request (HttpMethod method, Class<T> clazz) throws JsonProcessingException {
		return RestTestUtil.request(ENDPOINT_URL, null, method, clazz);
	}
	
	public <T, D> T request (D data, HttpMethod method, Class<T> clazz) throws JsonProcessingException {
		return RestTestUtil.request(ENDPOINT_URL, data, method, clazz);
	}
	
	public <T, D> T request (String uid, D data,HttpMethod method, Class<T> clazz) throws JsonProcessingException {
		return RestTestUtil.request(UID_ENDPOINT_URL, data, method, clazz, uid);
	}
	
	public <T, D> T request (String uid ,HttpMethod method, Class<T> clazz) throws JsonProcessingException {
		return RestTestUtil.request(UID_ENDPOINT_URL, null, method, clazz, uid);
	}
	
	public <T, D> T request (String uid ,HttpMethod method) throws JsonProcessingException {
		return RestTestUtil.request(UID_ENDPOINT_URL, null, method, null, uid);
	}
	
}

package cisco.assignment.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class RecordApiTestUtil {
	
	private final String ENDPOINT;
	private final String UID_ENDPOINT;
	
	@Autowired
	public RecordApiTestUtil(@Value("${endpoint.records}") String endpoint) {
		ENDPOINT = "http://localhost:8080"+endpoint;
		UID_ENDPOINT = ENDPOINT+"/{uid}";
	}
	
	public <T, D> T request (D data, HttpMethod method, Class<T> clazz) throws JsonProcessingException {
		return RestTestUtil.request(ENDPOINT, data, method, clazz);
	}
	
	public <T, D> T request (String uid, D data,HttpMethod method, Class<T> clazz) throws JsonProcessingException {
		return RestTestUtil.request(UID_ENDPOINT, data, method, clazz, uid);
	}
	
	public <T, D> T request (String uid ,HttpMethod method, Class<T> clazz) throws JsonProcessingException {
		return RestTestUtil.request(UID_ENDPOINT, null, method, clazz, uid);
	}
	
	public <T, D> T request (String uid ,HttpMethod method) throws JsonProcessingException {
		return RestTestUtil.request(UID_ENDPOINT, null, method, null, uid);
	}
	
}

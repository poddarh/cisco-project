package assignment.cisco.controller;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import assignment.cisco.ObjectsApplication;
import assignment.cisco.model.ObjectModel;
import assignment.cisco.repository.ObjectRepository;
import assignment.cisco.util.MapBuilder;

@SuppressWarnings("unused")

@WebIntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ObjectsApplication.class)
public class ObjectControllerTest {

	@Autowired
	private ObjectRepository objectRepository;
	private static final ObjectMapper OBJECT_MAPPER;
	private static final RestTemplate restTemplate;
	
	private static final String ENDPOINT;
	private static final String UID_ENDPOINT;

	static {
		OBJECT_MAPPER = new ObjectMapper();
		restTemplate = new TestRestTemplate();
		ENDPOINT = "http://localhost:8080/api/objects";
		UID_ENDPOINT = "http://localhost:8080/api/objects/{uid}";
	}

	@Test
	public void testInsert() throws JsonProcessingException {

		List<String> cities = Arrays.asList("Surat", "Vadodra","Ahmedabad","Amherst");
		Map<String, Object> map = MapBuilder.instance()
											.put("name", "Harsh Poddar")
											.put("dob", "07131996")
											.put("cities", cities)
											.build();
		
		HttpEntity<String> entity = generateHttpEntity(map);
		ObjectModel response = restTemplate.postForObject(ENDPOINT, entity, ObjectModel.class);
		
		assertNotNull(response);
		assertEquals(map,response.getData());
		
	}
	
	private HttpEntity<String> generateHttpEntity(Map<String, Object> map) throws JsonProcessingException {
		HttpHeaders requestHeaders = getBasicHeaders();
		String jsonData = jsonString(map);
		HttpEntity<String> entity = new HttpEntity<String>(jsonData, requestHeaders);
		return entity;
	}

	private HttpHeaders getBasicHeaders() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		return requestHeaders;
	}

	private String jsonString(Object obj) throws JsonProcessingException {
		return OBJECT_MAPPER.writeValueAsString(obj);
	}
	
	
	
}

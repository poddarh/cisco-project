package cisco.assignment.api;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import cisco.assignment.ObjectsApplication;
import cisco.assignment.EmbeddedMongoConfiguration;
import cisco.assignment.model.ErrorModel;
import cisco.assignment.model.ObjectModel;
import cisco.assignment.model.URL;
import cisco.assignment.repository.ObjectRepository;
import cisco.assignment.util.ObjectAPITestUtil;
import cisco.assignment.util.RestTestUtil;
import cisco.assignment.util.SampleMapData;

@WebIntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ObjectsApplication.class, EmbeddedMongoConfiguration.class})
public class ObjectAPITest {
	
	@Autowired ObjectAPITestUtil objCtrlTestUtil;
	@Autowired ObjectRepository objectRepository;
	
	@Before
	public void before(){
		objectRepository.deleteAll();
	}
	
	@Test
	public void testSimpleInsertAndGet() throws JsonProcessingException {
		
		Map<String, Object> map = SampleMapData.simpleData1();
		
		ObjectModel object1 = objCtrlTestUtil.request(map, HttpMethod.POST, ObjectModel.class);
		assertNotNull(object1);
		assertEquals(map, object1.getData());
		
		ObjectModel object2 = objCtrlTestUtil.request(object1.getUid(), HttpMethod.GET, ObjectModel.class);
		assertNotNull(object2);
		assertEquals(object1, object2);
		
	}
	
	@Test
	public void testNestedInsertAndGet() throws JsonProcessingException {

		Map<String, Object> map = SampleMapData.nestedData1();
		
		ObjectModel object1 = objCtrlTestUtil.request(map, HttpMethod.POST, ObjectModel.class);
		assertNotNull(object1);
		assertNotNull(object1.getUid());
		assertEquals(map,object1.getData());
		
		ObjectModel object2 = objCtrlTestUtil.request(object1.getUid(), HttpMethod.GET, ObjectModel.class);
		assertNotNull(object2);
		assertEquals(object1, object2);
		
	}
	
	@Test
	public void testRepeatNestedInsert() throws JsonProcessingException {
		
		Map<String, Object> map = SampleMapData.nestedData2();
		
		ObjectModel object1 = objCtrlTestUtil.request(map, HttpMethod.POST, ObjectModel.class);
		assertNotNull(object1);
		assertNotNull(object1.getUid());
		assertEquals(map,object1.getData());
		
		ObjectModel object2 = objCtrlTestUtil.request(map, HttpMethod.POST, ObjectModel.class);
		assertNotNull(object2);
		assertNotNull(object2.getUid());
		assertEquals(map,object2.getData());
		
		assertTrue(!object1.getUid().equals(object2.getUid()));
		
	}
	
	@Test
	public void testNestedUpdateAndGet() throws JsonProcessingException {
		
		Map<String, Object> map1 = SampleMapData.simpleData1();

		ObjectModel object1 = objCtrlTestUtil.request(map1, HttpMethod.POST, ObjectModel.class);
		assertNotNull(object1);
		assertNotNull(object1.getUid());
		assertEquals(map1,object1.getData());
		
		Map<String, Object> map2 = SampleMapData.nestedData1();
		ObjectModel requestObject = new ObjectModel(object1.getUid(), map2);
							
		ObjectModel object2 = objCtrlTestUtil.request(object1.getUid(), requestObject, HttpMethod.PUT, ObjectModel.class);
		assertNotNull(object2);
		assertEquals(requestObject, object2);
		
		ObjectModel object3 = objCtrlTestUtil.request(object1.getUid(), HttpMethod.GET, ObjectModel.class);
		assertNotNull(object3);
		assertEquals(object2, object3);
		
	}
	
	@Test
	public void testDeleteAndGet() throws JsonProcessingException {
		
		Map<String, Object> map1 = SampleMapData.simpleData1();
		Map<String, Object> map2 = SampleMapData.nestedData1();
		
		ObjectModel object1 = objCtrlTestUtil.request(map1, HttpMethod.POST, ObjectModel.class);
		assertNotNull(object1);
		assertNotNull(object1.getUid());
		assertEquals(map1,object1.getData());
		
		ObjectModel object2 = objCtrlTestUtil.request(map2, HttpMethod.POST, ObjectModel.class);
		assertNotNull(object2);
		assertNotNull(object2.getUid());
		assertEquals(map2,object2.getData());
		
		objCtrlTestUtil.request(object1.getUid(), HttpMethod.DELETE);
		ErrorModel error = objCtrlTestUtil.request(object1.getUid(), HttpMethod.GET, ErrorModel.class);
		assertNotNull(error);
		
		ObjectModel object3 = objCtrlTestUtil.request(object2.getUid(), HttpMethod.GET, ObjectModel.class);
		assertNotNull(object3);
		assertEquals(object2, object3);
		
	}
	
	@Test
	public void testGetAll() throws JsonProcessingException {
		
		Map<String, Object> map1 = SampleMapData.simpleData2();
		Map<String, Object> map2 = SampleMapData.nestedData2();
		
		ObjectModel object1 = objCtrlTestUtil.request(map1, HttpMethod.POST, ObjectModel.class);
		assertNotNull(object1);
		assertNotNull(object1.getUid());
		assertEquals(map1,object1.getData());
		
		ObjectModel object2 = objCtrlTestUtil.request(map2, HttpMethod.POST, ObjectModel.class);
		assertNotNull(object2);
		assertNotNull(object2.getUid());
		assertEquals(map2,object2.getData());
		
		List<URL> urls = asList(objCtrlTestUtil.request(null, HttpMethod.GET, URL[].class));
		assertEquals(urls.size(), 2);
		
		for (URL url : urls) {
			ObjectModel object = RestTestUtil.request(url.getUrl(), null, HttpMethod.GET, ObjectModel.class);
			assertTrue(object.equals(object1) || object.equals(object2) );
		}
		
	}
	
	
	
}

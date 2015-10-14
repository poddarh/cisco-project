package cisco.assignment.api;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import cisco.assignment.EmbeddedMongoConfiguration;
import cisco.assignment.RecordApplication;
import cisco.assignment.model.ErrorModel;
import cisco.assignment.model.Record;
import cisco.assignment.model.URL;
import cisco.assignment.repository.RecordRepository;
import cisco.assignment.util.RecordApiTestUtil;
import cisco.assignment.util.RestTestUtil;
import cisco.assignment.util.SampleMapData;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RecordApplication.class, EmbeddedMongoConfiguration.class})
@WebIntegrationTest(randomPort=true)
public class RecordApiTest {
	
	@Autowired RecordApiTestUtil recordApiTestUtil;
	@Autowired RecordRepository recordRepository;
	
	@Value("${local.server.port}") private int port;
	@Value("${endpoint.records}") private String endpoint;
	
	@PostConstruct
	private void configure() {
		recordApiTestUtil.configure(port, endpoint);
	}
	
	/**
	 * Clear the database before any test
	 */
	@Before
	public void before(){
		recordRepository.deleteAll();
	}
	
	@Test
	public void testSimpleInsertAndGet() throws JsonProcessingException {
		
		Map<String, Object> map = SampleMapData.simpleData1();
		
		Record record1 = recordApiTestUtil.request(map, HttpMethod.POST, Record.class);
		assertNotNull(record1);
		assertEquals(map, record1.getData());
		
		Record record2 = recordApiTestUtil.request(record1.getUid(), HttpMethod.GET, Record.class);
		assertNotNull(record2);
		assertEquals(record1, record2);
		
	}
	
	@Test
	public void testSimpleInsertWithUid() throws JsonProcessingException {
		
		Record record1 = new Record("6c799d1c4cb24eaeb8f37e0dd10ae2ea", SampleMapData.simpleData1());
		ErrorModel error1 = recordApiTestUtil.request(record1, HttpMethod.POST, ErrorModel.class);
		assertNotNull(error1);
		
		ErrorModel error2 = recordApiTestUtil.request(record1.getUid(), HttpMethod.GET, ErrorModel.class);
		assertNotNull(error2);
		
	}
	
	@Test
	public void testGetWithoutInserting() throws JsonProcessingException {
		
		ErrorModel error2 = recordApiTestUtil.request("6c799d1c4cb24eaeb8f37e0dd10ae2ea", HttpMethod.GET, ErrorModel.class);
		assertNotNull(error2);
		
	}
	
	@Test
	public void testNestedInsertAndGet() throws JsonProcessingException {

		Map<String, Object> map = SampleMapData.nestedData1();
		
		Record record1 = recordApiTestUtil.request(map, HttpMethod.POST, Record.class);
		assertNotNull(record1);
		assertNotNull(record1.getUid());
		assertEquals(map,record1.getData());
		
		Record record2 = recordApiTestUtil.request(record1.getUid(), HttpMethod.GET, Record.class);
		assertNotNull(record2);
		assertEquals(record1, record2);
		
	}
	
	@Test
	public void testRepeatNestedInsert() throws JsonProcessingException {
		
		Map<String, Object> map = SampleMapData.nestedData2();
		
		Record record1 = recordApiTestUtil.request(map, HttpMethod.POST, Record.class);
		assertNotNull(record1);
		assertNotNull(record1.getUid());
		assertEquals(map,record1.getData());
		
		Record record2 = recordApiTestUtil.request(map, HttpMethod.POST, Record.class);
		assertNotNull(record2);
		assertNotNull(record2.getUid());
		assertEquals(map,record2.getData());
		
		assertFalse(record1.getUid().equals(record2.getUid()));
		
	}
	
	@Test
	public void testUpdateWithoutId() throws JsonProcessingException {
		
		Map<String, Object> map1 = SampleMapData.simpleData1();
		
		Record record1 = recordApiTestUtil.request(map1, HttpMethod.POST, Record.class);
		assertNotNull(record1);
		assertNotNull(record1.getUid());
		assertEquals(map1,record1.getData());
		
		Map<String, Object> map2 = SampleMapData.nestedData2();
		
		Record record2 = recordApiTestUtil.request(record1.getUid(), map2, HttpMethod.PUT, Record.class);
		assertNotNull(record2);
		assertEquals(record1.getUid(), record2.getUid());
		assertEquals(map2, record2.getData());
		
	}
	
	@Test
	public void testUpdateId() throws JsonProcessingException {
		
		Map<String, Object> map1 = SampleMapData.simpleData1();
		
		Record record1 = recordApiTestUtil.request(map1, HttpMethod.POST, Record.class);
		assertNotNull(record1);
		assertNotNull(record1.getUid());
		assertEquals(map1,record1.getData());
		
		Map<String, Object> map2 = SampleMapData.nestedData2();
		Record requestObject = new Record(record1.getUid()+"123ab", map2);
		
		ErrorModel errorModel = recordApiTestUtil.request(record1.getUid(), requestObject, HttpMethod.PUT, ErrorModel.class);
		assertNotNull(errorModel);
		
		Record record2 = recordApiTestUtil.request(record1.getUid(), HttpMethod.GET, Record.class);
		assertNotNull(record2);
		assertEquals(record1, record2);
		
	}
	
	
	@Test
	public void testNestedUpdateAndGet() throws JsonProcessingException {
		
		Map<String, Object> map1 = SampleMapData.simpleData1();

		Record record1 = recordApiTestUtil.request(map1, HttpMethod.POST, Record.class);
		assertNotNull(record1);
		assertNotNull(record1.getUid());
		assertEquals(map1,record1.getData());
		
		Map<String, Object> map2 = SampleMapData.nestedData2();
		Record requestObject = new Record(record1.getUid(), map2);
							
		Record record2 = recordApiTestUtil.request(record1.getUid(), requestObject, HttpMethod.PUT, Record.class);
		assertNotNull(record2);
		assertEquals(requestObject, record2);
		
		Record record3 = recordApiTestUtil.request(record1.getUid(), HttpMethod.GET, Record.class);
		assertNotNull(record3);
		assertEquals(record2, record3);
		
	}
	
	@Test
	public void testDeleteAndGet() throws JsonProcessingException {
		
		Map<String, Object> map1 = SampleMapData.simpleData1();
		Map<String, Object> map2 = SampleMapData.nestedData1();
		
		Record record1 = recordApiTestUtil.request(map1, HttpMethod.POST, Record.class);
		assertNotNull(record1);
		assertNotNull(record1.getUid());
		assertEquals(map1,record1.getData());
		
		Record record2 = recordApiTestUtil.request(map2, HttpMethod.POST, Record.class);
		assertNotNull(record2);
		assertNotNull(record2.getUid());
		assertEquals(map2,record2.getData());
		
		recordApiTestUtil.request(record1.getUid(), HttpMethod.DELETE);
		ErrorModel error = recordApiTestUtil.request(record1.getUid(), HttpMethod.GET, ErrorModel.class);
		assertNotNull(error);
		
		Record record3 = recordApiTestUtil.request(record2.getUid(), HttpMethod.GET, Record.class);
		assertNotNull(record3);
		assertEquals(record2, record3);
		
	}
	
	@Test
	public void testEmptyGetAll() throws JsonProcessingException {
		
		List<URL> urls = asList(recordApiTestUtil.request(HttpMethod.GET, URL[].class));
		assertNotNull(urls);
		assertEquals(urls.size(), 0);
		
	}
	
	@Test
	public void testGetAll() throws JsonProcessingException {
		
		Map<String, Object> map1 = SampleMapData.simpleData2();
		Map<String, Object> map2 = SampleMapData.nestedData2();
		
		Record record1 = recordApiTestUtil.request(map1, HttpMethod.POST, Record.class);
		assertNotNull(record1);
		assertNotNull(record1.getUid());
		assertEquals(map1,record1.getData());
		
		Record record2 = recordApiTestUtil.request(map2, HttpMethod.POST, Record.class);
		assertNotNull(record2);
		assertNotNull(record2.getUid());
		assertEquals(map2,record2.getData());
		
		List<URL> urls = asList(recordApiTestUtil.request(HttpMethod.GET, URL[].class));
		assertNotNull(urls);
		assertEquals(urls.size(), 2);
		
		for (URL url : urls) {
			Record record = RestTestUtil.request(url.getUrl(), null, HttpMethod.GET, Record.class);
			assertTrue(record.equals(record1) || record.equals(record2) );
		}
		
	}
	
	
	
}

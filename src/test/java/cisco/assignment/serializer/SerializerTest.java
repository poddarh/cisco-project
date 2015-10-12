package cisco.assignment.serializer;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import cisco.assignment.model.Record;
import cisco.assignment.util.JsonUtil;
import cisco.assignment.util.SampleMapData;

public class SerializerTest {
	
	@Test
	public void testDeserialize() throws IOException {
		
		String simpleData1Json = "{\"uid\":\"6c799d1c4cb24eaeb8f37e0dd10ae2ea\",\"dob\":\"02171995\",\"name\":\"Aashna Poddar\"}";
		Map<String, Object> map = SampleMapData.simpleData1();
		Record simpleJsonRecord = new Record("6c799d1c4cb24eaeb8f37e0dd10ae2ea", map);
		
		Record parsedJsonRecord = JsonUtil.parse(simpleData1Json, Record.class);
		assertEquals(simpleJsonRecord, parsedJsonRecord);
		
	}
	
	@Test
	public void testSerialize() throws IOException {
		
		Map<String, Object> map = SampleMapData.nestedData1();
		Record simpleJsonRecord = new Record("6c799d1c4cb24eaeb8f37e0dd10ae2ea", map);
		
		String jsonString = JsonUtil.toJson(simpleJsonRecord);
		
		Record parsedJsonRecord = JsonUtil.parse(jsonString, Record.class);
		assertEquals(simpleJsonRecord, parsedJsonRecord);
		
	}
	
}

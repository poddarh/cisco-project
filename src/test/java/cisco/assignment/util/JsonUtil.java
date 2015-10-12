package cisco.assignment.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	private static final ObjectMapper OBJECT_MAPPER;
	
	static {
		OBJECT_MAPPER = new ObjectMapper();
	}
	
	public static String toJson(Object obj) throws JsonProcessingException {
		return OBJECT_MAPPER.writeValueAsString(obj);
	}
	
	public static <T> T parse(String str, Class<T> clazz) throws IOException {
		return OBJECT_MAPPER.readValue(str,clazz);
	}
	
}

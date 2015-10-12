package cisco.assignment.serializer;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import cisco.assignment.exception.BadRequestException;
import cisco.assignment.model.Record;

public class RecordDeserializer extends JsonDeserializer<Record>{
	
	private static final TypeReference<Map<String, Object>> TYPE_REFERENCE;
	static{
		TYPE_REFERENCE = new TypeReference<Map<String, Object>>() {};
	}
	
	@Override
	public Class<Record> handledType() {
		return Record.class;
	}
	
	@Override
	public Record deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		Map<String, Object> data = jp.readValueAs(TYPE_REFERENCE);
		
		Record model = new Record();
		Object uidObj = data.remove("uid");
		
		if(uidObj != null){
			
			String uid;
			if(uidObj instanceof String)
				uid = (String) uidObj;
			else
				throw new BadRequestException("'uid' should be a string");
			
			model.setUid(uid);
		}
		
		model.setData(data);
		
		return model;
	}
	
}

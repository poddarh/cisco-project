package assignment.cisco.serializer;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import assignment.cisco.exception.BadRequestException;
import assignment.cisco.model.ObjectModel;

public class ObjectModelDeserializer extends JsonDeserializer<ObjectModel>{
	
	private static final TypeReference<Map<String, Object>> TYPE_REFERENCE;
	static{
		TYPE_REFERENCE = new TypeReference<Map<String, Object>>() {};
	}
	
	@Override
	public Class<ObjectModel> handledType() {
		return ObjectModel.class;
	}
	
	@Override
	public ObjectModel deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		Map<String, Object> data = jp.readValueAs(TYPE_REFERENCE);
		
		ObjectModel model = new ObjectModel();
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

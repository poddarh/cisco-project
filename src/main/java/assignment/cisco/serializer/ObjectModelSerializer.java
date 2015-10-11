package assignment.cisco.serializer;

import java.io.IOException;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import assignment.cisco.model.ObjectModel;

public class ObjectModelSerializer extends JsonSerializer<ObjectModel>{
	
	@Override
	public Class<ObjectModel> handledType() {
		return ObjectModel.class;
	}
	
	@Override
	public void serialize(ObjectModel value, JsonGenerator g, SerializerProvider provider)
			throws IOException,JsonProcessingException {
		
		g.writeStartObject();
		
		if(value.getUid() != null)
			g.writeStringField("uid", value.getUid());
		
		if(value.getData()!=null){
			for(Entry<String, Object> entry : value.getData().entrySet()){
				g.writeObjectField(entry.getKey(), entry.getValue());
			}
		}
		
	    g.writeEndObject();
	    
	}
	
}

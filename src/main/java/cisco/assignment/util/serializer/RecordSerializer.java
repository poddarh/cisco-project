package cisco.assignment.util.serializer;

import java.io.IOException;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import cisco.assignment.model.Record;

/**
 * Used to serialize the {@link Record} model
 * @author harsh
 *
 */
public class RecordSerializer extends JsonSerializer<Record>{
	
	@Override
	public Class<Record> handledType() {
		return Record.class;
	}
	
	@Override
	public void serialize(Record value, JsonGenerator g, SerializerProvider provider)
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

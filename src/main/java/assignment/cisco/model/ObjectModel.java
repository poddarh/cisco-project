package assignment.cisco.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import assignment.cisco.serializer.ObjectModelDeserializer;
import assignment.cisco.serializer.ObjectModelSerializer;

@Document(collection="objects")
@JsonSerialize(using = ObjectModelSerializer.class)
@JsonDeserialize(using = ObjectModelDeserializer.class)
public class ObjectModel {
	
	@Id @Indexed
	private String uid;
	private Map<String, Object> data;
	
	public ObjectModel() {}
	public ObjectModel(String uid) {
		this.uid = uid;
	}
	public ObjectModel(Map<String, Object> data) {
		this.data = data;
	}
	public ObjectModel(String uid, Map<String, Object> data) {
		this.uid = uid;
		this.data = data;
	}
	
	// Getters and setters...
	public final String getUid() {
		return uid;
	}
	public final void setUid(String uid) {
		this.uid = uid;
	}
	public final Map<String, Object> getData() {
		return data;
	}
	public final void setData(Map<String, Object> data) {
		this.data = data;
	}
}

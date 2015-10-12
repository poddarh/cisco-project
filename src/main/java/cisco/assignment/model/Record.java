package cisco.assignment.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cisco.assignment.serializer.RecordDeserializer;
import cisco.assignment.serializer.RecordSerializer;

@Document(collection="records")
@JsonSerialize(using = RecordSerializer.class)
@JsonDeserialize(using = RecordDeserializer.class)
public class Record {
	
	@Id @Indexed
	private String uid;
	private Map<String, Object> data;
	
	public Record() {}
	public Record(String uid) {
		this.uid = uid;
	}
	public Record(Map<String, Object> data) {
		this.data = data;
	}
	public Record(String uid, Map<String, Object> data) {
		this.uid = uid;
		this.data = data;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
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

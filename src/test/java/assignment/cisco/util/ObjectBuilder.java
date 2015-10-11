package assignment.cisco.util;

import java.util.TreeMap;

import assignment.cisco.model.ObjectModel;

public class ObjectBuilder {
	private final ObjectModel MODEL;
	private ObjectBuilder() {
		MODEL = new ObjectModel();
	}
	
	public static ObjectBuilder instance() {
		return new ObjectBuilder();
	}
	
	public final ObjectBuilder setUid(String uid) {
		MODEL.setUid(uid);
		return this;
	}
	
	public final ObjectBuilder put(String key, Object value) {
		if(MODEL.getData() == null)
			MODEL.setData(new TreeMap<>());
		MODEL.getData().put(key, value);
		return this;
	}
	
	public final ObjectModel build() {
		return MODEL;
	}
	
	
}

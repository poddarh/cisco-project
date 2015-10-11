package cisco.assignment.util;

import java.util.Map;
import java.util.TreeMap;

public class MapBuilder {
	private final Map<String, Object> MAP;
	private MapBuilder() {
		MAP = new TreeMap<>();
	}
	
	public static MapBuilder instance() {
		return new MapBuilder();
	}
	
	public final MapBuilder put(String key, Object value) {
		MAP.put(key, value);
		return this;
	}
	
	public final Map<String, Object> build() {
		return MAP;
	}
	
	
}

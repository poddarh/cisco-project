package cisco.assignment.util;

import static java.util.Arrays.asList;

import java.util.Map;

public class SampleMapData {
	
	public static Map<String, Object> simpleData1(){
		return MapBuilder.instance()
				 		 .put("name", "Aashna Poddar")
				 		 .put("dob", "02171995")
				 		 .build();
	}
	
	public static Map<String, Object> simpleData2(){
		return MapBuilder.instance()
						 .put("computing", "A*")
						 .put("physics", "A*")
						 .put("math", "A")
						 .put("chemistry", "A")
						 .build();
	}
	
	public static Map<String, Object> nestedData1(){
		return MapBuilder.instance()
						 .put("fullname", "Harsh Poddar")
						 .put("work", "Intern at a startup by Cisco")
						 .put("cities", asList("Surat", "Ahmedabad", "Amherst", "Cambridge"))
						 .build();
	}
	
	public static Map<String, Object> nestedData2(){
		Map<String, Object> radius = MapBuilder.instance()
											   .put("inner", "1")
											   .put("outer", "2")
											   .build();
		return MapBuilder.instance()
						 .put("type", "washer")
						 .put("height", "3")
						 .put("radius", radius)
						 .build();
	}
	
}

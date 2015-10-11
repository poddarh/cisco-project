package assignment.cisco.service;

import java.util.List;

import assignment.cisco.model.ObjectModel;
import assignment.cisco.model.URL;

public interface ObjectService {
	List<URL> getUrls();
	ObjectModel find(String id);
	ObjectModel insert(ObjectModel object);
	ObjectModel update(ObjectModel object);
	void delete(String id);
}

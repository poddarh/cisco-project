package cisco.assignment.service;

import java.util.List;

import cisco.assignment.model.ObjectModel;

public interface ObjectService {
	List<String> getAllUids();
	ObjectModel find(String id);
	ObjectModel insert(ObjectModel object);
	ObjectModel update(ObjectModel object);
	void delete(String id);
}

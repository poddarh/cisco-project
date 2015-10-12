package cisco.assignment.service;

import java.util.List;

import cisco.assignment.model.Record;

public interface RecordService {
	List<String> getAllUids();
	Record find(String id);
	Record insert(Record object);
	Record update(Record object);
	void delete(String id);
}

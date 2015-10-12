package cisco.assignment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cisco.assignment.exception.NoSuchRecordException;
import cisco.assignment.model.Record;
import cisco.assignment.repository.RecordRepository;

@Service
public class SimpleRecordService implements RecordService {
	
	private final RecordRepository recordRepository;
	
	@Autowired
	public SimpleRecordService(RecordRepository recordRepository) {
		this.recordRepository = recordRepository;
	}

	@Override
	public List<String> getAllUids() {
		List<Record> records = recordRepository.findAllIds();
		return records.stream()
						   .map(Record::getUid)
						   .collect(Collectors.toList());
	}

	@Override
	public Record find(String id) {
		Record object = recordRepository.findOne(id);
		if(object == null)
			throw new NoSuchRecordException();
		return object;
	}
	
	@Override
	public Record insert(Record object) {
		return recordRepository.save(object);
	}

	@Override
	public Record update(Record object) {
		if(!recordRepository.exists(object.getUid()))
			throw new NoSuchRecordException();
		return recordRepository.save(object);
	}

	@Override
	public void delete(String id) {
		if(!recordRepository.exists(id))
			throw new NoSuchRecordException();
		recordRepository.delete(id);
	}

}

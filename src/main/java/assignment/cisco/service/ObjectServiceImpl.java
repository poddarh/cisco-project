package assignment.cisco.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import assignment.cisco.exception.NoSuchRecordException;
import assignment.cisco.model.ObjectModel;
import assignment.cisco.repository.ObjectRepository;

@Service
public class ObjectServiceImpl implements ObjectService {
	
	private final ObjectRepository objectRepository;
	
	@Autowired
	public ObjectServiceImpl(ObjectRepository objectRepository) {
		this.objectRepository = objectRepository;
	}

	@Override
	public List<String> getAllUids() {
		List<ObjectModel> objectModels = objectRepository.findAllIds();
		return objectModels.stream()
						   .map(ObjectModel::getUid)
						   .collect(Collectors.toList());
	}

	@Override
	public ObjectModel find(String id) {
		ObjectModel object = objectRepository.findOne(id);
		if(object == null)
			throw new NoSuchRecordException();
		return object;
	}
	
	@Override
	public ObjectModel insert(ObjectModel object) {
		return objectRepository.insert(object);
	}

	@Override
	public ObjectModel update(ObjectModel object) {
		if(!objectRepository.exists(object.getUid()))
			throw new NoSuchRecordException();
		return objectRepository.save(object);
	}

	@Override
	public void delete(String id) {
		if(!objectRepository.exists(id))
			throw new NoSuchRecordException();
		objectRepository.delete(id);
	}

}

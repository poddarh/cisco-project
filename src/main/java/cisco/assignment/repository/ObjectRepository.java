package cisco.assignment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import cisco.assignment.model.ObjectModel;

public interface ObjectRepository extends MongoRepository<ObjectModel, String> {
	@Query("{},{ _id: 1}")
	List<ObjectModel> findAllIds();
}

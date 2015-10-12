package cisco.assignment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cisco.assignment.model.ObjectModel;

public interface ObjectRepository extends CrudRepository<ObjectModel, String> {
	@Query("{},{ _id: 1}")
	List<ObjectModel> findAllIds();
}

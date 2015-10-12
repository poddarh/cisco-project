package cisco.assignment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cisco.assignment.model.Record;

public interface RecordRepository extends CrudRepository<Record, String> {
	@Query("{},{ _id: 1}")
	List<Record> findAllIds();
}

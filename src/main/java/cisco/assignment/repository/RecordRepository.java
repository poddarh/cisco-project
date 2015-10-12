package cisco.assignment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cisco.assignment.model.Record;


/**
 * A generic repository to perform CRUD operations with Record objects. This interface is auto-implemented by Spring Data.
 * 
 * @author harsh
 *
 */
public interface RecordRepository extends CrudRepository<Record, String> {
	/**
	 * 
	 * @return a list of all the Record objects with only the uid field populated
	 */
	@Query("{},{ _id: 1}")
	List<Record> findAllIds();
}

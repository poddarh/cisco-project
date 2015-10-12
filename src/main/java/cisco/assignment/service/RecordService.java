package cisco.assignment.service;

import java.util.List;

import cisco.assignment.model.Record;

public interface RecordService {
	
	/**
	 * 
	 * @return a list of uid of all records.
	 */
	List<String> getAllUids();
	
	/**
	 * 
	 * Returns the record associated with the <code>id</code> provided.
	 * 
	 * @param id the id of the record
	 * @return a record
	 */
	Record find(String id);
	
	/**
	 * Creates a new record.
	 * 
	 * @param object to be inserted
	 * @return the inserted object
	 */
	Record insert(Record record);
	
	/**
	 * 
	 * Updates a record associated with the <code>id</code> in the record.
	 * 
	 * @param record - changed record
	 * @return the updated record
	 */
	Record update(Record record);
	
	/**
	 * Deletes the record associated with the <code>id</code> provided.
	 * 
	 * @param id of the record
	 */
	void delete(String id);
}

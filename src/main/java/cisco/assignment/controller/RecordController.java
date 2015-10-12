package cisco.assignment.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cisco.assignment.exception.BadRequestException;
import cisco.assignment.exception.NoSuchRecordException;
import cisco.assignment.exception.RestException;
import cisco.assignment.model.Record;
import cisco.assignment.model.URL;
import cisco.assignment.service.RecordService;
import cisco.assignment.util.HttpRequestUtil;

@RestController
@RequestMapping(value="${endpoint.records}", produces="application/json")
public class RecordController {
	
	private RecordService recordService;
	private HttpRequestUtil requestUtil;
	
	@Autowired
	public RecordController(RecordService recordService, HttpRequestUtil requestUtil) {
		super();
		this.recordService = recordService;
		this.requestUtil = requestUtil;
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<URL> findAll(@Value("${endpoint.records}") String endpoint) {
		
		List<String> ids = recordService.getAllUids();
		String baseURL = requestUtil.getBaseURL();
		
		List<URL> urls = ids.stream()
							.map(id -> new URL(baseURL + endpoint + "/" + id))
							.collect(Collectors.toList());
		return urls;
	}

	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public Record find(@PathVariable String uid) {
		return recordService.find(uid);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public Record save(@RequestBody Record model) {
		
		if(model.getUid() != null)
			throw new BadRequestException("Cannot POST a uid in the object.");
		
		return recordService.insert(model);
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.PUT, consumes="application/json")
	public Record update(@PathVariable String uid, @RequestBody Record model) {
		
		if(!uid.equals(model.getUid()))
			throw new BadRequestException("Cannot change uid");
		
		return recordService.update(model);
		
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.DELETE)
	public void delete(@PathVariable String uid) throws NoSuchRecordException {
		recordService.delete(uid);
	}
	
	@RequestMapping("/**")
	public void handleRequestsNotSupported() {
		throw new RestException("This method is not allowed at this endpoint", HttpStatus.METHOD_NOT_ALLOWED);
	}
	
}

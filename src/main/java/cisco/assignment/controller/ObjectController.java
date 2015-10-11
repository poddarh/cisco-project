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
import cisco.assignment.model.ObjectModel;
import cisco.assignment.model.URL;
import cisco.assignment.service.ObjectService;
import cisco.assignment.util.HttpRequestUtil;

@RestController
@RequestMapping(value="${endpoint.objects}", produces="application/json")
public class ObjectController {
	
	private ObjectService objectService;
	private HttpRequestUtil requestUtil;
	
	@Autowired
	public ObjectController(ObjectService objectService, HttpRequestUtil requestUtil) {
		super();
		this.objectService = objectService;
		this.requestUtil = requestUtil;
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<URL> findAll(@Value("${endpoint.objects}") String endpoint) {
		
		List<String> ids = objectService.getAllUids();
		String baseURL = requestUtil.getBaseURL();
		
		List<URL> urls = ids.stream()
							.map(id -> new URL(baseURL + endpoint + "/" + id))
							.collect(Collectors.toList());
		return urls;
	}

	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public ObjectModel find(@PathVariable String uid) {
		return objectService.find(uid);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ObjectModel save(@RequestBody ObjectModel model) {
		
		if(model.getUid() != null)
			throw new BadRequestException("Cannot POST a uid in the object.");
		
		return objectService.insert(model);
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.PUT, consumes="application/json")
	public ObjectModel update(@PathVariable String uid, @RequestBody ObjectModel model) {
		
		if(!uid.equals(model.getUid()))
			throw new BadRequestException("Cannot change uid");
		
		return objectService.update(model);
		
	}
	
	@RequestMapping(value="/{uid}", method=RequestMethod.DELETE)
	public void delete(@PathVariable String uid) throws NoSuchRecordException {
		objectService.delete(uid);
	}
	
	@RequestMapping("/**")
	public void handleRequestsNotSupported() {
		throw new RestException("This method is not allowed at this endpoint", HttpStatus.METHOD_NOT_ALLOWED);
	}
	
}

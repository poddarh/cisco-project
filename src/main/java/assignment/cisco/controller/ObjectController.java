package assignment.cisco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import assignment.cisco.model.ObjectModel;
import assignment.cisco.model.URL;
import assignment.cisco.service.ObjectService;
import assignment.cisco.util.exception.BadRequestException;
import assignment.cisco.util.exception.NoSuchRecordException;
import assignment.cisco.util.exception.RestException;

@RestController
@RequestMapping(value="${endpoint.objects}", produces="application/json")
public class ObjectController {
	private ObjectService objectService;
	
	@Autowired
	public ObjectController(ObjectService objectService) {
		this.objectService = objectService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<URL> findAll() {
		return objectService.getUrls();
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
package com.pet.clinic.contoller;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.clinic.dto.DescriptionRequest;
import com.pet.clinic.entity.Description;
import com.pet.clinic.service.DescriptionService;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
// This class handles incoming model, retrieves necessary models data and return appropriate response
public class DescriptionController {

	@Autowired 
	DescriptionService descriptionService;

	private static Logger logger = LogManager.getLogger(DescriptionController.class);
	
	// This method used to call insert description method in service class which is used to insert description record.
	@PostMapping("/descriptions")
	public ResponseEntity<Description> insertDescription(@RequestBody DescriptionRequest descriptionRequest) {
		logger.debug("In insertDescription method ");
		return new ResponseEntity<Description>((Description) descriptionService.insertDescription(descriptionRequest),HttpStatus.OK);
	}

	// This method used to call delete description method in service class which is used to delete description record by using description id.
	@DeleteMapping("/descriptions/{Id}")
	public ResponseEntity<String> deleteDescription(@PathVariable Integer Id) {
		logger.debug("In deleteDescription method ");
		return new ResponseEntity<String>(descriptionService.deleteDescriptionById(Id), HttpStatus.OK);

	}

	// This method used to call getById description method in service class which is used to get description record.
	@GetMapping("/descriptions/{petId}")
	public Description getById(@PathVariable Integer petId) {
		logger.debug("In getById method ");
		return descriptionService.descriptionById(petId);

	}

	// This method used to call update description method in service class which is used to update description record.
	@PatchMapping("/descriptions/{id}")
	public Description updateDescription(@RequestBody DescriptionRequest descriptionRequest, @PathVariable int id) {
		logger.debug("In updateDescription method ");
		return descriptionService.descriptionUpdate(descriptionRequest, id);
	}
	
	//This method used to call findAll method in service class which is used to get all records from Description table.
	@GetMapping("/description-List")
	public List<Description> getDescriptionList()
	{
		logger.debug("In getDescriptionList method ");
		return descriptionService.findAll();
	}
	
	//This method used to call updateDescription method in service class which is used to update description record.
	@PutMapping("/description-update")
	public Description updateDescription(@RequestBody DescriptionRequest descriptionRequest) {
		logger.debug("In updateDescription method ");
		return descriptionService.updateDescription(descriptionRequest);
		
	}
}
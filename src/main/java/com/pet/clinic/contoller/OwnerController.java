package com.pet.clinic.contoller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Base64;
import java.util.Base64.Decoder;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pet.clinic.dto.OwnerRequest;
import com.pet.clinic.entity.OwnerEntity;
import com.pet.clinic.service.DoctorService;
import com.pet.clinic.service.OwnerService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
//This class handles incoming model, retrieves necessary model data and return appropriate response.
public class OwnerController {

	@Autowired
	OwnerService ownerService;

	@Autowired
	DoctorService doctorService;

	private static Logger logger = LogManager.getLogger(OwnerController.class);

	// This method is use to call inserOwner() method in service class which is used to insert the Owner record.
	@PostMapping("/save-owner")
	public ResponseEntity<OwnerEntity> insertOwner(@RequestBody OwnerRequest ownerRequest) {
		logger.debug("hi" + ownerRequest.getPassword());
		ownerRequest.setNote("aa");
		return new ResponseEntity<OwnerEntity>((OwnerEntity) ownerService.insertOwner(ownerRequest), HttpStatus.OK);
	}


	//This method used for validation purpose.
	@GetMapping("/user-validation")
	public ResponseEntity<String> findByUsernameAndPassword(@RequestParam("username") String username,
															@RequestParam("password") String password, @RequestParam("logger") String logger) {
				String doctorResponse="";
		if ("Doctor".equals(logger)) {
			doctorResponse = doctorService.findByUsernameAndPassword(username, password);
		} else if ("Owner".equals(logger)) {
			 doctorResponse = ownerService.findByUsernameAndPassword(username, password);
				System.out.println("doctorResponse"+doctorResponse);
		}
		if(doctorResponse=="User not available")
		{
			return new ResponseEntity<String>(doctorResponse, HttpStatus.OK);

		}else
		{
		return new ResponseEntity<String>(doctorResponse, HttpStatus.OK);

			}
	}



	// This method is use to call getById() method in service class which is used to get the Owner record.
	@GetMapping("owners/{id}")
	public Optional<OwnerEntity> getOwnerById(@PathVariable int id) 
	{
		logger.debug("In getOwnerById methode id is:"+id);
		return ownerService.ownerById(id);
	}

	// This method is use to call deleteOwner() method in service class which is used to delete the Owner record.
	@DeleteMapping("owners/{id}")
	public ResponseEntity<String> deleteOwner(@PathVariable("id") int id) {
         logger.debug("In delete method");
		return new ResponseEntity<String>(ownerService.deleteById(id), HttpStatus.OK);
	}

	// This method is use to call updateOwner() method in service class which is used to update the Owner record.
	@PatchMapping("owners/{id}")
	public ResponseEntity<OwnerEntity> updateOwner(@RequestBody OwnerRequest ownerRequest, @PathVariable int id) {
		logger.debug("In updateOwner method ");
		return new ResponseEntity<OwnerEntity>((OwnerEntity) ownerService.ownerUpdate(ownerRequest, id), HttpStatus.OK);
	}
	
	// This method is use to call findAll() method in service class which is used to fetch all record from database .
	@GetMapping("/owner-List")
	public List<OwnerEntity> getOwnerList()
	{
	logger.debug("In getOwnerList method ");	
		return ownerService.findAll();
	}
	
	// This method is use to call updateOwner() method in service class which is used to update record.
	@PutMapping("owners")
	public OwnerEntity updateOwner(@RequestBody OwnerRequest ownerRequest) {
		logger.debug("In updateOwner method ");
		return ownerService.updateOwner(ownerRequest);
		
	}
	
	// This method is use to call deleteOwner() method in service class which is used to delete record from database .
	@DeleteMapping("owners-byId/{id}")
	public void deleteOwner1(@PathVariable int id) {
		logger.debug("In deleteOwner method ");
		ownerService.deleteOwner(id);
	}

	@GetMapping("/owner-List/{username}")
	public List<OwnerEntity> getOwnerList(@PathVariable String username)
	{
		logger.debug("In getOwnerList method ");
		//return ownerService.findAll();
		return  ownerService.findByUsername(username);

	}

	@GetMapping("/Owner-List/{username}")
	List<OwnerEntity> findOwner(@PathVariable String username){
		return ownerService.findOwner( username);
	}

//	@GetMapping("/owner-findusername/{username}")
//	public String ownerUsername(@PathVariable String username){
//
//		return  ownerService.ownerUserName(username);
//	}
	@GetMapping("/owner-findusername/{username}")
	public Object ownerUsername(@PathVariable String username){
		return  ownerService.getByUsername(username);
	}


//	@GetMapping("/owner-findEmail/{email}")
//	public String ownerEmail(@PathVariable String email){
//		return  ownerService.ownerEmail(email);
//	}

	@GetMapping("/owner-byEmail/{email}")
	public Object getOwnerByEmail(@PathVariable String email){
		return ownerService.getByEmail(email);
	}
}
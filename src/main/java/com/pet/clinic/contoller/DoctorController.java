package com.pet.clinic.contoller;

import java.util.List;

import com.pet.clinic.dto.OwnerPet;
import com.pet.clinic.entity.OwnerEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.pet.clinic.dto.DoctorRegistrationRequest;
import com.pet.clinic.entity.Doctor;
import com.pet.clinic.service.DoctorService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
//This class handles incoming model,retrieves necessary model data and return appropriate responses. 
public class DoctorController {
	
	private static Logger logger = LogManager.getLogger(DoctorController.class);
	
	@Autowired
	DoctorService doctorService;

	@PostMapping("/save-doctor")
	// This method is use to call insertDoctor() method in service class which is
	// used to insert the Doctor record.
	public Doctor insertDoctorDetail(@RequestBody DoctorRegistrationRequest doctorRegistrationRequest) {
		logger.debug("insert method in doctor controller");
		return doctorService.insertDoctor(doctorRegistrationRequest);
	}

//This method is use to call getDoctorById() method in service class which is used to get doctor record by it's id.
	@GetMapping("doctors/{id}")
	public Doctor getDoctorById(@PathVariable int id) {
		logger.debug("getById method in doctor controller");
		//logger.error("intered into Get metod "+ id);
		return doctorService.doctorById(id);
	}

//This method is use to call deleteDoctorById() method in service class which is used to delete doctor record by it's id.
	@DeleteMapping("doctors/{id}")
	public String deleteDoctorById(@PathVariable int id) {
		logger.debug("deleteDoctorById method in doctor controller");
		return doctorService.doctorDeleteById(id);
	}

	@PutMapping("/doctors/{id}")
//This method is used to call the doctorUpdate method in service class which is used to update doctor's record if necessary.	
	public Doctor updateDoctor(@RequestBody DoctorRegistrationRequest doctorRegistrationRequest, @PathVariable int id) {
		logger.debug("Patch method in doctor controller");
		return doctorService.doctorUpdate(doctorRegistrationRequest, id);
	}
	
	@GetMapping("/Doctor-List")
	public List<Doctor> getAllDoctor() {
		
		logger.debug("GetAll method in doctor controller");
		return doctorService.findAll();
	}
	@GetMapping("/Doctor-List/{username}")
	public List<Doctor> findbyDoctorname(@PathVariable String username)
	{
		//return ownerService.findAll();
		return  doctorService.findbyDoctorname(username);
	}
	
	
//	@PutMapping("doctors")
//	public Doctor updateDoctor(@RequestBody DoctorRegistrationRequest doctorRegistrationRequest){
//		logger.debug("fullUpdate method in doctor controller");
//		return doctorService.doctorUpdate(doctorRegistrationRequest);
//
//	}


	@GetMapping("/doctor-username/{username}")
	public String doctorUsername(@PathVariable String username){

		return  doctorService.doctorUsername(username);
	}

	@GetMapping("/doctor-byUsername/{username}")
	public Object getDoctorByUsername(@PathVariable String username){
		return doctorService.getByUsername(username);
	}



}

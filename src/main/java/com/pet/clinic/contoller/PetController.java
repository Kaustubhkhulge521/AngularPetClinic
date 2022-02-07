package com.pet.clinic.contoller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.pet.clinic.dto.OwnerPet;
import com.pet.clinic.entity.OwnerEntity;
import com.pet.clinic.repository.OwnerRepository;
import com.pet.clinic.repository.PetRepository;
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

import com.pet.clinic.dto.PetRequest;
import com.pet.clinic.entity.PetEntity;
import com.pet.clinic.service.PetService;

//This class handles incoming model, retrieves necessary model data and returns appropriate response
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
public class PetController {

	@Autowired
	PetService petService;

	@Autowired
	PetRepository petRepository;

	@Autowired
	OwnerRepository ownerRepository;
	
	private static Logger logger = LogManager.getLogger(PetController.class);
	

	//This method is used to call addPet() method from PetService class which  insert the record  into pet table
	@PostMapping("/pet")
	public ResponseEntity<PetEntity> addPet(@RequestBody PetRequest petRequest) {
		System.out.println("Data"+petRequest);
		logger.debug("Enter in addPet() method");
		return new ResponseEntity<PetEntity>((PetEntity) petService.addPet(petRequest), HttpStatus.OK);
	}

		
	
    //This method is used to call updatePet() method from service class which update the record if required.
	@PatchMapping("/pet/{petId}")
	public ResponseEntity<PetEntity> updatePet(@PathVariable Integer petId , @RequestBody PetRequest petRequest) {
		logger.debug("Enter in updatePet() method");
		return new ResponseEntity<PetEntity>( petService.updatePet(petId, petRequest), HttpStatus.OK);
	}
	
    //This method is used to call seletePetById() method from service class which delete the record by using petId. 
	@DeleteMapping("/pet/{petId}")
	public ResponseEntity<String> deletePet(@PathVariable Integer petId) {
		logger.debug("Enter in deletePet() method");
		return new ResponseEntity<String>(petService.deletePetById(petId), HttpStatus.OK);

	}
	
	//This method is used to call getPetById() method from service class which fetch the record from pet table by petId.
	@GetMapping("/pet/{petId}")
	public Optional<PetEntity> getPetById(@PathVariable Integer petId) {
		logger.debug("Enter in getPetById() method");
		return	petService.getPetById(petId);
	}
	
	//This method is used to call findAll() method from service class which fetch all record from pet table.
	@GetMapping("/pet-List")
	public List<PetEntity> getPetList()
	{
		logger.debug("Enter in getPetList() method");
		List<PetEntity>List=petService.findAll();
		System.out.println("List::"+List);
		return List ;
	}
	
	//This method is used to call petUpdate() method from service class which update record into database.
	@PutMapping("/pet-update")
	public PetEntity petUpdate(@RequestBody PetRequest petRequest) {
		logger.debug("Enter in petUpdate() method");
		return	petService.petUpdate(petRequest);
		
	}
	@GetMapping("/pet-List/{username}")
	public List<PetEntity> findPetByusername(@PathVariable String username)
	{
		logger.debug("In getPetList method ");
		//return ownerService.findAll();
		return  petService.findPetByusername(username);
	}



	@GetMapping("/Get-ownerPetData")
	public  List<Map<String,String>> getAllPetOwnerData(){
		return petRepository.findAllDataFromPetAndOwner();
	}

	@PutMapping("/update-petOwner/{id}")
	public OwnerPet updateAllData(@RequestBody OwnerPet ownerPet, @PathVariable Integer id){
		OwnerEntity owner = ownerRepository.getById(id);
		owner.setId(id);
		owner.setOwnerName(ownerPet.getOwnerName());
		owner.setEmail(ownerPet.getEmail());
		owner.setMobileNumber(ownerPet.getMobileNumber());
		owner.setNote(ownerPet.getNote());
		owner.setUsername(ownerPet.getUsername());
		ownerRepository.save(owner);

		PetEntity pet =petRepository.findById(id).get();
		pet.setPetName(ownerPet.getPetName());
		pet.setSpeciesId(ownerPet.getSpeciesId());
		pet.setPetSpecies(ownerPet.getPetSpecies());
		pet.setPetSymptoms(ownerPet.getPetSymptoms());
		petRepository.save(pet);
		return ownerPet;
	}
}
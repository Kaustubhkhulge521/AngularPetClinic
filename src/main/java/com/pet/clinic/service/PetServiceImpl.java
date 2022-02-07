package com.pet.clinic.service;

import java.util.List;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.clinic.dto.PetRequest;
import com.pet.clinic.entity.PetEntity;
import com.pet.clinic.repository.PetRepository;

@Service
// This class provide actual implementation for all the required methods.
public class PetServiceImpl implements PetService {

	@Autowired
	PetRepository petRepository;


	private static Logger logger = LogManager.getLogger(PetServiceImpl.class);


	// This method insert the record into Pet Table
	@Override
	public PetEntity addPet(PetRequest petRequest) {
		PetEntity petEntity = new PetEntity();
		petEntity.setPetId(petRequest.getPetId());
		String petName = petRequest.getPetName();
		Pattern patrPetName = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
		Matcher matchPetname = patrPetName.matcher(petName);
		if (matchPetname.find() && matchPetname.group().equals(petName))
			petEntity.setPetName(petName);
		else
			throw new Error("PetName must include only a-z OR A-Z");
		String username = petRequest.getUsername();
		Pattern patrOwnerName = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
		Matcher matchOwnerName = patrOwnerName.matcher(username);
		if (matchOwnerName.find() && matchOwnerName.group().equals(username))
			petEntity.setUsername(username);
		else
			throw new Error("OwnerName must include only a-z OR A-Z");
		petEntity.setSpeciesId(petRequest.getSpeciesId());
		String petSpecies = petRequest.getPetSpecies();
		Pattern patrPetSpecies = Pattern.compile("^[a-zA-Z][a-zA-Z0-9 ]*$");
		Matcher matchPetSpecies = patrPetSpecies.matcher(petSpecies);
		if (matchPetSpecies.find() && matchPetSpecies.group().equals(petSpecies))
			petEntity.setPetSpecies(petSpecies);
		else
			throw new Error("PetSpecies must include only a-z OR A-Z OR 0-9");

		String petSymptoms = petRequest.getPetSymptoms();
		Pattern patrPetSymptoms = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
		Matcher matchPetSymptoms = patrPetSymptoms.matcher(petSymptoms);
		if (matchPetSymptoms.find() && matchPetSymptoms.group().equals(petSymptoms))
			petEntity.setPetSymptoms(petSymptoms);
		else
			throw new Error("PetSymptoms must include only a-z OR A-Z");

		petEntity.setDelete(false);
		petRepository.save(petEntity);
		logger.debug("pet record is: " + petEntity);
		return petEntity;
	}

	// This method will delete pet record from the Pet Table and return String
	@Override
	public String deletePetById(Integer petId) {
		PetEntity petEntity = new PetEntity();
		petEntity = petRepository.getById(petId);
		if (petEntity.isDelete() == false) {
			petEntity.setDelete(true);
		}
		petRepository.save(petEntity);
		logger.debug("Pet deleted isDelete status is: " + petEntity.isDelete());
		return "Pet deleted successfully";
	}


	// This method will update pet record into pet Table
	@Override
	public PetEntity updatePet(Integer petId, PetRequest petRequest) {
		PetEntity petEntity = new PetEntity();
		petEntity = petRepository.getById(petId);
		if (petRequest.getPetId() != 0)
			petEntity.setPetId(petRequest.getPetId());
		String PetName = petRequest.getPetName();
		if (PetName != null) {
			Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
			Matcher matchPetname = pattern.matcher(PetName);
			if (matchPetname.find() && matchPetname.group().equals(PetName))
				petEntity.setPetName(PetName);
			else
				throw new Error("PetName must include only a-z OR A-Z");
		}

		String username = petRequest.getUsername();
		if (username != null) {
			Pattern patternOwnerName = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
			Matcher matchPetOwnerName = patternOwnerName.matcher(username);
			if (matchPetOwnerName.find() && matchPetOwnerName.group().equals(username))
				petEntity.setUsername(username);
			else
				throw new Error("OwnerName must include only a-z OR A-Z");
		}
		if (petRequest.getSpeciesId() != 0)
			petEntity.setSpeciesId(petRequest.getSpeciesId());

		String petSpecies = petRequest.getPetSpecies();
		if (petSpecies != null) {
			Pattern patternPetSpecies = Pattern.compile("^[a-zA-Z][a-zA-Z0-9 ]*$");
			Matcher matchPetSpecies = patternPetSpecies.matcher(petSpecies);
			if (matchPetSpecies.find() && matchPetSpecies.group().equals(petSpecies))
				petEntity.setPetSpecies(petSpecies);
			else
				throw new Error("PetSpecies must include only a-z OR A-Z OR 0-9");

		}

		String petSymptoms = petRequest.getPetSymptoms();
		if (petSymptoms != null) {
			Pattern patternPetSymptoms = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
			Matcher matchPetSymptoms = patternPetSymptoms.matcher(petSymptoms);
			if (matchPetSymptoms.find() && matchPetSymptoms.group().equals(petSymptoms))
				petEntity.setPetSymptoms(petSymptoms);
			else
				throw new Error("PetSymptoms must include only a-z OR A-Z");

		}
		petEntity.setDelete(false);
		petEntity = petRepository.save(petEntity);
		logger.debug("updated pet is: " + petEntity);
		return petEntity;
	}

	// This method will used to fetch pet record from pet Table by using petId
	@Override
	public Optional<PetEntity> getPetById(Integer petId) {
		Optional<PetEntity> petEntity = petRepository.findById(petId);
		logger.debug("pet Id is : " + petId);
		return petEntity;
	}

	@Override
	public List<PetEntity> findAll() {
		// TODO Auto-generated method stub
		logger.debug("In findAll methode od serviceImpl");
		return petRepository.findByisDelete(false);
	}

	@Override
	public PetEntity petUpdate(PetRequest petRequest) {
		PetEntity petEntity = new PetEntity();
		petEntity.setPetId(petRequest.getPetId());
		petEntity.setUsername(petRequest.getUsername());
		//petEntity.setOwnerName(petRequest.getOwnerName());
		petEntity.setPetName(petRequest.getPetName());
		petEntity.setPetSpecies(petRequest.getPetSpecies());
		petEntity.setSpeciesId(petRequest.getSpeciesId());
		petEntity.setPetSymptoms(petRequest.getPetSymptoms());
		petRepository.save(petEntity);
		logger.debug("updated pet is: " + petEntity);
		return petEntity;
	}

	@Override
	public List<PetEntity> findPetByusername(String username) {
		return petRepository.findByusername(username);
	}


}
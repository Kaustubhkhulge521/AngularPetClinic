package com.pet.clinic.service;

import java.util.List;

import java.util.Optional;
import com.pet.clinic.dto.PetRequest;
import com.pet.clinic.entity.PetEntity;

//This interface is used to hide actual implementation of the methods.
public interface PetService {

	PetEntity addPet(PetRequest petRequest);

	String deletePetById(Integer petId);

	PetEntity updatePet(Integer petId, PetRequest petRequest);

	Optional<PetEntity> getPetById(Integer petId);

	List<PetEntity> findAll();
	
	PetEntity petUpdate(PetRequest petRequest);

	List<PetEntity> findPetByusername(String username);
	

}
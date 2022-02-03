package com.pet.clinic.service;

import java.util.List;
import java.util.Optional;

import com.pet.clinic.dto.OwnerRequest;
import com.pet.clinic.entity.OwnerEntity;

//This interface is used to hide actual implementation of the method.
public interface OwnerService {

	OwnerEntity insertOwner(OwnerRequest ownerRequest);

	String deleteById(int id);

	OwnerEntity ownerUpdate(OwnerRequest ownerRequest, int id);

	Optional<OwnerEntity> ownerById(int id);

	String findByUsernameAndPassword(String username, String password);
	List<OwnerEntity> findAll();

	OwnerEntity updateOwner(OwnerRequest ownerRequest);

	List<OwnerEntity> findByUsername(String username);
	void deleteOwner(int id);

	List<OwnerEntity> findOwner(String username);

	//String ownerUserName(String username);

	 Object getByUsername(String username);

	 Object getByEmail(String email);

	//String ownerEmail(String email);
}


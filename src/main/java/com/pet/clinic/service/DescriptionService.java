package com.pet.clinic.service;

import java.util.List;

import com.pet.clinic.dto.DescriptionRequest;
import com.pet.clinic.entity.Description;

//This interface is used to hide actual implementation of the method.
public interface DescriptionService {

	Description insertDescription(DescriptionRequest descriptionRequest);

	Description descriptionById(Integer petId);

	Description descriptionUpdate(DescriptionRequest descriptionRequest, int id);

	String deleteDescriptionById(Integer Id);

	List<Description> findAll();
	
	Description updateDescription(DescriptionRequest descriptionRequest);

}
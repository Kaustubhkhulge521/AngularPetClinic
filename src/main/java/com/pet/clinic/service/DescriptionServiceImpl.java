package com.pet.clinic.service;
import java.util.List;


import com.pet.clinic.entity.OwnerEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.clinic.dto.DescriptionRequest;
import com.pet.clinic.entity.Description;
import com.pet.clinic.repository.DescriptionRepo;


@Service
// This class provide implementation for all the required method.
public class DescriptionServiceImpl implements DescriptionService {
	private final DescriptionRepo descriptionRepo;

	
	@Autowired
	public DescriptionServiceImpl(DescriptionRepo descriptionRepo) {
		this.descriptionRepo = descriptionRepo;
	}

	private static Logger logger = LogManager.getLogger(DescriptionServiceImpl.class);
	
	Description description = new Description();

	// This method will insert data in pet description table
	@Override
	public Description insertDescription(DescriptionRequest descriptionRequest) {
		description.setPetId(descriptionRequest.getPetId());
		description.setPetName(descriptionRequest.getPetName());
		description.setSpeciesId(descriptionRequest.getSpeciesId());
		description.setPetBreed(descriptionRequest.getPetBreed());
		description.setPetDisease(descriptionRequest.getPetDisease());
		description.setMedicine(descriptionRequest.getMedicine());
		description.setMedicineDose(descriptionRequest.getMedicineDose());
		description.setDelete(false);
		descriptionRepo.save(description);
		logger.debug("description is : "+description);
		return description;
	}

	// This method will get record from the pet description table by using id
	@Override
	public Description descriptionById(Integer petId) {
		
		return descriptionRepo.findById(petId).orElse(description);
	}

	// This method will update pet record into description table
	@Override
	public Description descriptionUpdate(DescriptionRequest descriptionRequest, int id) {
		description = descriptionRepo.findById(id).orElse(description);
		if (descriptionRequest.getPetId() != 0)
			description.setPetId(descriptionRequest.getPetId());
		if (descriptionRequest.getPetName() != null)
			description.setPetName(descriptionRequest.getPetName());
		if (descriptionRequest.getSpeciesId() != 0)
			description.setSpeciesId(descriptionRequest.getSpeciesId());
		if (descriptionRequest.getPetBreed() != null)
			description.setPetBreed(descriptionRequest.getPetBreed());
		if (descriptionRequest.getPetDisease() != null)
			description.setPetDisease(descriptionRequest.getPetDisease());
		if (descriptionRequest.getMedicine() != null)
			description.setMedicine(descriptionRequest.getMedicine());
		if (descriptionRequest.getMedicineDose() != null)
			description.setMedicineDose(descriptionRequest.getMedicineDose());
		description.setDelete(false);
		descriptionRepo.save(description);
		logger.debug("Updated description is :"+description);
		return description;
	}

	// This method will delete record by using id
	@Override
	public String deleteDescriptionById(Integer id) {

		/*descriptionRepo.getById(id);
		if (description.isDelete() == false) {
			description.setDelete(true);
			descriptionRepo.save(description);
		}*/
		Description description = new Description();

		description = descriptionRepo.getById(id);
		if (description.isDelete() == false) {
			description.setDelete(true);
			descriptionRepo.save(description);
		}
		logger.debug("Owner id is: "+ id);

		
		logger.debug("description deleted isDelete Status:"+description.isDelete());
		return "description deleted Successfully";
	}
	
	//this method will fetch all record from database
	@Override
	public List<Description> findAll() {
		logger.debug("In FindAll method of serviceImpl class");
		return descriptionRepo.findAllUndeletedRecords();
	}

	//This method will update pet record into description table
	@Override
	public Description updateDescription(DescriptionRequest descriptionRequest) {
		description.setPetId(descriptionRequest.getPetId());
		description.setPetName(descriptionRequest.getPetName());
		description.setSpeciesId(descriptionRequest.getSpeciesId());
		description.setPetBreed(descriptionRequest.getPetBreed());
		description.setPetDisease(descriptionRequest.getPetDisease());
		description.setMedicine(descriptionRequest.getMedicine());
		description.setMedicineDose(descriptionRequest.getMedicineDose());
		description.setDelete(false);
		descriptionRepo.save(description);
		logger.debug("Updated description is: "+description);
		return description;
	}
}
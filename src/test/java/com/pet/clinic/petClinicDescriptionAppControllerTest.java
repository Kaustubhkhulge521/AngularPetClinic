package com.pet.clinic;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.pet.clinic.contoller.DescriptionController;
import com.pet.clinic.dto.DescriptionRequest;
import com.pet.clinic.entity.Description;
import com.pet.clinic.service.DescriptionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
// This class used to proved unit testing for all required methods of controller class.
public class petClinicDescriptionAppControllerTest {

	@MockBean
	DescriptionService descriptionService;

	@Autowired
	DescriptionController descriptionController;

	Description description = new Description();

	// This method used to provide unit testing for insert description method of controller class.
	@Test
	public void testInsertDescription() {
		Description description = dummyDescription();
		DescriptionRequest descriptionRequest = dummyDescriptionRequest();
		when(descriptionService.insertDescription(descriptionRequest)).thenReturn(description);
		ResponseEntity<Description> response = descriptionController.insertDescription(descriptionRequest);
		assertNotNull(response);
	}

	DescriptionRequest dummyDescriptionRequest() {
		DescriptionRequest descriptionRequest = new DescriptionRequest();
		descriptionRequest.setPetId(1);
		descriptionRequest.setPetName("Dog");
		descriptionRequest.setSpeciesId(12);
		descriptionRequest.setPetBreed("Normal");
		descriptionRequest.setPetDisease("headInjury");
		descriptionRequest.setMedicine("cayclonia");
		descriptionRequest.setMedicineDose("first");
		return descriptionRequest;
	}

	Description dummyDescription() {
		DescriptionRequest descriptionRequest = dummyDescriptionRequest();
		description.setPetId(descriptionRequest.getPetId());
		description.setPetName(descriptionRequest.getPetName());
		description.setSpeciesId(descriptionRequest.getSpeciesId());
		description.setPetBreed(descriptionRequest.getPetBreed());
		description.setPetDisease(descriptionRequest.getPetDisease());
		description.setMedicine(descriptionRequest.getMedicine());
		description.setMedicineDose(descriptionRequest.getMedicineDose());
		description.setDelete(false);
		return description;
	}

	// This method used to provide unit testing for delete description method of
	// controller class.
	@Test
	public void test_deleteDescription() {
		description = dummyDescription();
		String res = "description deleted Successfully";
		description.setDelete(true);
		when(descriptionService.deleteDescriptionById(1)).thenReturn(res);
		ResponseEntity<String> response = descriptionController.deleteDescription(1);
		assertNotNull(response);

	}

	// This method used to provide unit testing for get description method of
	// controller class.
	@Test
	public void test_descriptionGetById() {
		description = dummyDescription();
		Integer id = 1;
		when(descriptionService.descriptionById(1)).thenReturn(description);
		Description response = descriptionController.getById(1);
		assertNotNull(description);
		assertNotNull(response);
	}

	// This method used to provide unit testing for update description method of
	// controller class.
	@Test
	public void testUpdate() {
		Description description = dummyDescription();
		Integer id = 1;
		when(descriptionService.descriptionUpdate(dummyDescriptionRequest(), id)).thenReturn(description);
		Description response = descriptionController.updateDescription(dummyDescriptionRequest(), id);
		assertNull(response);
	}

}
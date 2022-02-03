package com.pet.clinic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.pet.clinic.dto.DescriptionRequest;
import com.pet.clinic.entity.Description;
import com.pet.clinic.repository.DescriptionRepo;
import com.pet.clinic.service.DescriptionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
// This method used to provide unit testing for all required method of serviceImpl class.
public class PetClinicAppDescriptionImpl {

    @MockBean
    DescriptionRepo descriptionRepo;

    @Autowired
    DescriptionServiceImpl descriptionServiceImpl;

    DescriptionRequest descriptionRequest = new DescriptionRequest();
    Description description = new Description();

    // This method used to provide unit testing for insert description method of
    // serviceImpl class.
    @Test
    public void testInsertDescription() {
        Description description = dummyDescription();
        DescriptionRequest descriptionRequest = dummyDescriptionRequest();
        when(descriptionRepo.save(dummyDescription())).thenReturn(description);
        descriptionRepo.save(description);
        Description response = descriptionServiceImpl.insertDescription(descriptionRequest);
        assertNotNull(response);
    }

    DescriptionRequest dummyDescriptionRequest() {

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
        descriptionRepo.save(description);
        return description;
    }

    // This method used to provide unit testing for delete description method of
    // serviceImpl class.
    @Test
    public void test_deleteDescription() {
        description = dummyDescription();
        description.setDelete(true);
        when(descriptionRepo.getById(1)).thenReturn(description);
        String response = descriptionServiceImpl.deleteDescriptionById(1);
        assertNotNull(response);
        assertEquals("description deleted Successfully", response);
    }

    // This method used to provide unit testing for get description method of
    // serviceImpl class.
    @Test
    public void test_descriptionGetById() {
        description = dummyDescription();
        Integer id = 1;
        Description response = descriptionServiceImpl.descriptionById(1);
        when(descriptionRepo.getById(1)).thenReturn(response);
        assertNotNull(description);
        assertNotNull(response);
    }

    //// This method used to provide unit testing for update description method of
    //// serviceImpl class.
    @Test
    public void testUpdate() {
        Description description = dummyDescription();
        Integer id = 1;
        when(descriptionRepo.getById(1)).thenReturn(description);
        description = descriptionRepo.save(description);
        Description response = descriptionServiceImpl.descriptionUpdate(descriptionRequest, id);
        assertNotNull(response);
    }

}

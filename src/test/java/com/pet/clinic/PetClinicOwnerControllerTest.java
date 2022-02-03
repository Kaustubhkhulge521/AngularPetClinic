package com.pet.clinic;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.pet.clinic.contoller.OwnerController;
import com.pet.clinic.dto.OwnerRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.pet.clinic.entity.OwnerEntity;
import com.pet.clinic.service.OwnerService;

//This class used to proved Unit testing for all required methods of controller class
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PetClinicOwnerControllerTest {

    @MockBean
    OwnerService ownerService;

    @Autowired
    OwnerController ownerController;

    OwnerRequest ownerRequest = new OwnerRequest();
    OwnerEntity owner = new OwnerEntity();

    //This method used to provide unit testing for insertOwner() method of Controller class
    @Test
    public void testInsertOwner() {
        OwnerRequest ownerRequest = dummyOwnerRequest();
        OwnerEntity owner = dummyOwnerEntity();

        when(ownerService.insertOwner(ownerRequest)).thenReturn(owner);
        ownerService.insertOwner(ownerRequest);

        ResponseEntity<OwnerEntity> response = ownerController.insertOwner(ownerRequest);
        assertNotNull(response);

    }

    public OwnerRequest dummyOwnerRequest() {

        ownerRequest.setId(1);
        ownerRequest.setOwnerName("jayu");
       // ownerRequest.setLastName("sapkale");
        ownerRequest.setEmail("sapkale@gmail.com");
        ownerRequest.setMobileNumber("9867400939");
        ownerRequest.setNote("hello");
        ownerRequest.setDelete(false);

        return ownerRequest;
    }

    public OwnerEntity dummyOwnerEntity() {
        OwnerRequest ownerRequest = dummyOwnerRequest();

        owner.setId(ownerRequest.getId());
        owner.setOwnerName(ownerRequest.getOwnerName());
        //owner.setLastName(ownerRequest.getLastName());
        owner.setEmail(ownerRequest.getEmail());
        owner.setMobileNumber(ownerRequest.getMobileNumber());
        owner.setNote(ownerRequest.getNote());
        owner.setDelete(false);
        return owner;
    }
//This method used to provide unit testing for deleteOwner() method of Controller class

    @Test
    public void testDeleteOwner() {
        OwnerRequest owner = dummyOwnerRequest();
        owner.setDelete(false);
        Integer id = 1;
        ownerService.deleteById(id);
        ResponseEntity<String> response = ownerController.deleteOwner(id);
        assertNotNull(response);

    }
    // This method used to provide unit testing for updateOwner() method of Controller class

    @Test
    public void testUpdateOwner() {
        OwnerRequest ownerRequest = dummyOwnerRequest();
        OwnerEntity owner = dummyOwnerEntity();
        Integer id = 1;
        when(ownerService.ownerUpdate(ownerRequest, id)).thenReturn(owner);
        ResponseEntity<OwnerEntity> response = ownerController.updateOwner(ownerRequest, id);
        assertNotNull(response);

    }
    // This method used to provide unit testing for getOwner() method of Controller class

    @Test
    public void testGetById() {
        Integer id = 1;
        Optional<OwnerEntity> ownerEntity = ownerService.ownerById(id);
        when(ownerService.ownerById(id)).thenReturn(ownerEntity);
        Optional<OwnerEntity> response = ownerController.getOwnerById(id);
        assertNotNull(response);
    }

}

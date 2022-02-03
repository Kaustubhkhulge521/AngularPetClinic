package com.pet.clinic;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.pet.clinic.contoller.PetController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.pet.clinic.dto.PetRequest;
import com.pet.clinic.entity.PetEntity;
import com.pet.clinic.service.PetService;

//This class used to proved Unit testing for all required methods of  Controller class
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PetClinicPetControllerTest {

    @Autowired
    PetController petController;

    @MockBean
    PetService petService;

    //This method used to provide unit testing for addPet() method of Controller class
    @Test
    public void test_addPet() {
        PetRequest petRequest = dummyPetRequest();
        PetEntity petEntity = dummyPetEntity();
        when(petService.addPet(petRequest)).thenReturn(petEntity);
        ResponseEntity<PetEntity> response = petController.addPet(petRequest);
        assertNotNull(response);
    }

    //This method used to provide unit testing for deletePet() method of Controller class
    @Test
    public void test_deletePet() {
        PetEntity petEntity = dummyPetEntity();
        petEntity.setDelete(false);
        Integer petId = 3;
        petService.deletePetById(petId);
        ResponseEntity<String> response = petController.deletePet(petId);
        assertNotNull(response);
    }

    //This method used to provide unit testing for updatePet() method of Controller class
    @Test
    public void test_updatePet() {
        PetRequest petRequest =dummyPetRequest();
        PetEntity petEntity = dummyPetEntity();
        Integer petId = 3;
        when(petService.updatePet(petId, petRequest)).thenReturn(petEntity);
        ResponseEntity<PetEntity> reponse = petController.updatePet(petId, petRequest);
        assertNotNull(reponse);
    }
    //This method used to provide unit testing for getPetById() method of Controller class
    @Test
    public void test_getPet() {
        Integer petId = 3;
        Optional<PetEntity> petEntity = petService.getPetById(petId);
        when(petService.getPetById(petId)).thenReturn(petEntity);
        Optional<PetEntity>  response = petController.getPetById(petId);
        assertNotNull(response);

    }

    PetRequest petRequest = new PetRequest();

    PetEntity petEntity = new PetEntity();

    //This method used to create dummy recored for PetEntity object
    public PetEntity dummyPetEntity() {
        PetRequest petRequest =dummyPetRequest();
        PetEntity petEntity = new PetEntity();
        petEntity.setPetId(petRequest.getPetId());
        petEntity.setPetName(petRequest.getPetName());
        petEntity.setUsername(petRequest.getUsername());
        petEntity.setSpeciesId(petRequest.getSpeciesId());
        petEntity.setPetSpecies(petRequest.getPetSpecies());
        petEntity.setPetSymptoms(petRequest.getPetSymptoms());
        petEntity.setDelete(true);
        return petEntity;
    }

    //This method used to create dummy recored for PetRequest object
    public PetRequest dummyPetRequest() {
        petRequest.setPetId(3);
        petRequest.setPetName("cow");
        petRequest.setUsername("Jhon");
        petRequest.setSpeciesId(32);
        petRequest.setPetSpecies("sindhi");
        petRequest.setPetSymptoms("feavor");
        petRequest.setDelete(false);
        return petRequest;
    }

}

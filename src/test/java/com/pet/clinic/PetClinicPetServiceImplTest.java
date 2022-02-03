package com.pet.clinic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pet.clinic.dto.PetRequest;
import com.pet.clinic.entity.PetEntity;
import com.pet.clinic.repository.PetRepository;
import com.pet.clinic.service.PetService;

//This class used to proved Unit testing for all required methods of ServiceImpl class
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PetClinicPetServiceImplTest {

    @Autowired
    PetService petService;

    @MockBean
    PetRepository petRepository;

    // This method used to provide unit testing for addPet() method of serviceImpl class
    @Test
    public void test_addPet() {
        PetEntity petEntity = dummyPetEntity();
        PetRequest petRequest = dummyPetRequest();
        when(petRepository.save(dummyPetEntity())).thenReturn(petEntity);
        petEntity = petRepository.save(petEntity);
        PetEntity response = petService.addPet(petRequest);
        assertNotNull(response);
    }

    // This method used to provide unit testing or deletePet() method of ServiceImpl class
    @Test
    public void test_deletePet() {
        PetEntity petEntity = dummyPetEntity();
        petEntity.setDelete(false);
        Integer petId = 1;
        when(petRepository.getById(petId)).thenReturn(petEntity);
        petRepository.save(petEntity);
        String response = petService.deletePetById(petId);
        assertNotNull(response);
        assertEquals("Pet deleted successfully", response);
    }

    // This method used to provide unit testing for getPet() method of serviceImpl class
    @Test
    public void test_getPet() {
        Integer petId = 3;
        petRepository.findById(petId);
        Optional<PetEntity> responce = petService.getPetById(petId);
        assertNotNull(responce);
    }

    // This method used to provide unit testing for updatePet() method of serviceImpl class
    @Test
    public void test_updatePet() {
        PetRequest petRequest = dummyPetRequest();
        PetEntity petEntity = dummyPetEntity();
        Integer petId = 3;
        when(petRepository.getById(petId)).thenReturn(petEntity);
        PetEntity response = petService.updatePet(petId, petRequest);
        assertNull(response);

    }

    // This method used to create dummy recored for PetEntity object
    PetEntity dummyPetEntity() {
        PetRequest petRequest = dummyPetRequest();
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

    // This method used to create dummy recored for PetRequest object
    PetRequest dummyPetRequest() {
        PetRequest petRequest = new PetRequest();
        petRequest.setPetId(3);
        petRequest.setPetName("cow");
        petRequest.setUsername("Jhon");
        petRequest.setSpeciesId(32);
        petRequest.setPetSpecies("sindhi");
        petRequest.setPetSymptoms("feavor");
        petRequest.setDelete(true);
        return petRequest;

    }

}

package com.pet.clinic;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.pet.clinic.dto.OwnerRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pet.clinic.entity.OwnerEntity;
import com.pet.clinic.repository.OwnerRepository;
import com.pet.clinic.service.OwnerServiceImpl;
//This method used to provide unit testing for all required method of ServiceImpl class

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class PetClinicOwnerServiceTest {

    @MockBean
    OwnerRepository ownerRepository;

    @Autowired
    OwnerServiceImpl ownerServiceImpl;

    // This method used to provide unit testing for insertOwner() method of ServiceImpl class

    @Test
    public void testInsertOwner() {
        OwnerRequest ownerRequest = dummyOwnerRequest();
        OwnerEntity owner = dummyOwnerEntity();
        when(ownerRepository.save(dummyOwnerEntity())).thenReturn(owner);
        owner = ownerRepository.save(owner);
        OwnerEntity response = ownerServiceImpl.insertOwner(ownerRequest);
        assertNotNull(response);
    }

    public OwnerRequest dummyOwnerRequest() {
        OwnerRequest ownerRequest = new OwnerRequest();

        ownerRequest.setId(1);
        ownerRequest.setOwnerName("jayu");
       // ownerRequest.setLastName("sapkale");
        ownerRequest.setEmail("sapkale@gmail.com");
        ownerRequest.setMobileNumber("9867400939");
        ownerRequest.setNote("hello");
        ownerRequest.setDelete(false);
        ownerRequest.setOwnerName("owername");
        ownerRequest.setPassword("password");
        return ownerRequest;
    }

    public OwnerEntity dummyOwnerEntity() {
        OwnerRequest ownerRequest = dummyOwnerRequest();
        OwnerEntity owner = new OwnerEntity();
        owner.setId(ownerRequest.getId());
        owner.setOwnerName(ownerRequest.getOwnerName());
        //owner.setLastName(ownerRequest.getLastName());
        owner.setEmail(ownerRequest.getEmail());
        owner.setMobileNumber(ownerRequest.getMobileNumber());
        owner.setNote(ownerRequest.getNote());
        owner.setDelete(false);
        return owner;
    }
    // This method used to provide unit testing for deleteOwner() method of ServiceImpl class

    @Test
    public void test_deleteOwner() {
        OwnerEntity ownerEntity = dummyOwnerEntity();
        OwnerEntity owner = dummyOwnerEntity();
        when(ownerRepository.getById(ownerEntity.getId())).thenReturn(owner);
        ownerRepository.save(ownerEntity);
        String responce = ownerServiceImpl.deleteById(ownerEntity.getId());
        assertNotNull(responce);
    }
    // This method used to provide unit testing for updateOwner() method of ServiceImpl class

    @Test
    public void test_updateOwner() {
        OwnerRequest ownerRequest = dummyOwnerRequest();

        OwnerEntity owner = dummyOwnerEntity();
        Integer id = 1;
        when(ownerRepository.getById(id)).thenReturn(owner);
        owner = ownerRepository.save(owner);
        OwnerEntity response = ownerServiceImpl.ownerUpdate(ownerRequest, id);

    }
    // This method used to provide unit testing for getByIdOwner() method of ServiceImpl class

    @Test
    public void test_getById() {
        Integer id = 1;
        ownerRepository.findById(id);
        Optional<OwnerEntity> response = ownerServiceImpl.ownerById(id);
        System.out.println(" response " + response);

        assertNotNull(response);

    }
}

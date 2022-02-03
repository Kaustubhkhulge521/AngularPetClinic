package com.pet.clinic;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pet.clinic.contoller.DoctorController;
import com.pet.clinic.dto.DoctorRegistrationRequest;
import com.pet.clinic.entity.Doctor;
import com.pet.clinic.service.DoctorServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
//This class contain all junit test cases for controller class.
public class PetClinicControllerTest {

	@MockBean
	DoctorServiceImpl doctorServiceImpl;

	@Autowired
	DoctorController doctorController;

	@Test
	// This method contain Junit controller class test case for inserting doctor
	// detail.
	public void test_insertDoctor() {
		DoctorRegistrationRequest doctorRegistrationRequest = dummyDoctorRegistrationRequest();
		Doctor doctor = dummyDoctor();
		when(doctorServiceImpl.insertDoctor(doctorRegistrationRequest)).thenReturn(doctor);
		Doctor responce = doctorController.insertDoctorDetail(doctorRegistrationRequest);
		assertNotNull(responce);
	}

	DoctorRegistrationRequest doctorRegistrationRequest = new DoctorRegistrationRequest();
	Doctor doctor = new Doctor();

//This method method return doctorRegistrationRequest object which is further required for most of the Junit testing methods in this class 
	public DoctorRegistrationRequest dummyDoctorRegistrationRequest() {
		doctorRegistrationRequest.setId(1);
		doctorRegistrationRequest.setName("Chetan");
		doctorRegistrationRequest.setAddress("pune");
		doctorRegistrationRequest.setDesignation("Gastroenterologist");
		doctorRegistrationRequest.setQualification("MD");
		doctorRegistrationRequest.setMobileNumber("9876876892");
		return doctorRegistrationRequest;
	}

//This method method return doctor object which is further required for most of the Junit testing methods in this class 
	public Doctor dummyDoctor() {
		DoctorRegistrationRequest doctorRegistrationRequest = dummyDoctorRegistrationRequest();
		doctor.setId(doctorRegistrationRequest.getId());
		doctor.setName(doctorRegistrationRequest.getName());
		doctor.setDesignation(doctorRegistrationRequest.getDesignation());
		doctor.setQualification(doctorRegistrationRequest.getQualification());
		doctor.setAddress(doctorRegistrationRequest.getAddress());
		doctor.setMobileNumber(doctorRegistrationRequest.getMobileNumber());
		doctor.setDelete(false);
		return doctor;
	}

	// This method contain Junit controller class test case for getting single
	// doctor record By it's Id doctor detail
	@Test
	public void test_getById() {
		Doctor doctor = dummyDoctor();
		when(doctorServiceImpl.doctorById(1)).thenReturn(doctor);
		Doctor responce = doctorController.getDoctorById(1);
		assertNotNull(responce);

	}

	// This method contain Junit controller test case for deleting doctor detail by
	// It's Id
	@Test
	public void test_deleteById() {
		when(doctorServiceImpl.doctorDeleteById(1)).thenReturn("record deleted successfully");
		String response = doctorController.deleteDoctorById(1);
		assertEquals("record deleted successfully", response);

	}

	// This method contain Junit controller class test case for updating doctor
	// detail detail if required.
	@Test
	public void test_updateById() {
		int id = 1;
		DoctorRegistrationRequest doctorRegistrationRequest = dummyDoctorRegistrationRequest();
		when(doctorServiceImpl.doctorUpdate(doctorRegistrationRequest, id)).thenReturn(doctor);
		Doctor response = doctorController.updateDoctor(doctorRegistrationRequest, id);
		assertNotNull(response);
	}
}
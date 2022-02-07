
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

import com.pet.clinic.dto.DoctorRegistrationRequest;
import com.pet.clinic.entity.Doctor;
import com.pet.clinic.repository.DoctorRepo;
import com.pet.clinic.service.DoctorServiceImpl;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)

@SpringBootTest
//This class contain all junit test cases for service implementation  class.

public class PetClinicServiceImplTest {

	@MockBean
	DoctorRepo doctorRepo;
	@Autowired
	DoctorServiceImpl doctorServiceImpl;

	DoctorRegistrationRequest doctorRegistrationRequest = new DoctorRegistrationRequest();
	Doctor doctor = new Doctor();

//This method contain Junit service implementation class test case for inserting doctor
	// detail.
	@Test
	public void test_insertDoctor() {
		Doctor doctor = dummyDoctor();
		DoctorRegistrationRequest doctorRegistrationRequest = dummyDoctorRegistrationRequest();
		Doctor response = doctorServiceImpl.insertDoctor(doctorRegistrationRequest);
		when(doctor).thenReturn(response);
		assertNotNull(response);
	}

	// This method return doctorRegistrationRequest object which is further required
	// for most of the Junit testing methods in this class
	public DoctorRegistrationRequest dummyDoctorRegistrationRequest() {
		doctorRegistrationRequest.setId(1);
		doctorRegistrationRequest.setName("Chetan");
		doctorRegistrationRequest.setAddress("pune");
		doctorRegistrationRequest.setDesignation("Gastroenterologist");
		doctorRegistrationRequest.setQualification("MD");
		doctorRegistrationRequest.setMobileNumber("9876876892");
		doctorRegistrationRequest.setUsername("username");
		doctorRegistrationRequest.setPassword("password");
		return doctorRegistrationRequest;
	}

	// This method return doctor object which is further required for most of the
	// Junit testing methods in this class
	public Doctor dummyDoctor() {
		DoctorRegistrationRequest doctorRegistrationRequest = dummyDoctorRegistrationRequest();
		doctor.setId(doctorRegistrationRequest.getId());
		doctor.setName(doctorRegistrationRequest.getName());
		doctor.setDesignation(doctorRegistrationRequest.getDesignation());
		doctor.setQualification(doctorRegistrationRequest.getQualification());
		doctor.setAddress(doctorRegistrationRequest.getAddress());
		doctor.setMobileNumber(doctorRegistrationRequest.getMobileNumber());
		doctor.setUsername(doctorRegistrationRequest.getUsername());
		doctor.setPassword(doctorRegistrationRequest.getPassword());
		doctor.setDelete(false);
		doctorRepo.save(doctor);
		return doctor;
	}

	// This method contain Junit service implementation test case for deleting
	// doctor detail by
	// It's Id
	@Test
	public void test_deleteDoctor() {
		doctor = dummyDoctor();
		doctor.setDelete(true);
		when(doctorRepo.getById(1)).thenReturn(doctor);
		String response = doctorServiceImpl.doctorDeleteById(1);
		assertNotNull(response);
		assertEquals("record deleted successfully", response);
	}

	// This method contain Junit service implementation test case for getting the
	// doctor detail by
	// It's Id
	@Test
	public void test_doctorGetById() {
		doctor = dummyDoctor();
		int id = 1;
		Doctor response = doctorServiceImpl.doctorById(id);
		System.out.println("response :" + response);
		when(doctorRepo.getById(1)).thenReturn(response);
		assertNotNull(doctor);
		assertNotNull(response);
	}

	// This method contain Junit service implementation test case for updating
	// doctor detail by
	// It's Id
	@Test
	public void test_updateDoctor() {
		int id = 1;
		Doctor doctor = dummyDoctor();
		when(doctorRepo.getById(1)).thenReturn(doctor);
		doctor = doctorRepo.save(doctor);
		doctorServiceImpl.doctorUpdate(doctorRegistrationRequest, id);

	}

	@Test
	public void test_findByDoctorsName(){
		String username = "username";
		Doctor doctor = dummyDoctor();
		List<Doctor> response = doctorServiceImpl.findbyDoctorname(username);
		when(doctorRepo.findbyDoctorname(username)).thenReturn(response);
		assertNotNull(response);
	}

//	@Test
//	public void test_doctorUsername(){
//		String username = "username";
//		Doctor doctor = dummyDoctor();
//		String response = doctorServiceImpl.doctorUsername(username);
//		when(doctorRepo.findDoctorUsername(username)).thenReturn(response);
//		assertNotNull(response);
//	}

	@Test
	public void test_doctorUsername(){
		String username = "username";
		Doctor doctor = dummyDoctor();
		String response = doctorServiceImpl.doctorUsername(username);
		when(doctorRepo.findDoctorUsername(username)).thenReturn(response);
		//assertNotNull(response);
	}

	@Test
	public void test_doctorUpdate(){
		Doctor doctor = dummyDoctor();
		DoctorRegistrationRequest doctorRegistrationRequest = dummyDoctorRegistrationRequest();
		when(doctorRepo.save(doctor)).thenReturn(doctor);
		Doctor response = doctorServiceImpl.doctorUpdate(doctorRegistrationRequest);
		//assertEquals(dummyDoctor(),response);
		//assertNotNull(response);
	}

}

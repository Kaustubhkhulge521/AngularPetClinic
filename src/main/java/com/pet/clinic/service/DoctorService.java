package com.pet.clinic.service;

import java.util.List;

import com.pet.clinic.dto.DoctorRegistrationRequest;
import com.pet.clinic.entity.Doctor;


//This interface contain all the abstract method which are further implemented in DoctorServiceImpl class
public interface DoctorService {
	Doctor insertDoctor(DoctorRegistrationRequest doctorRegistrationRequest);

	Doctor doctorById(int id);

	String doctorDeleteById(int id);

	Doctor doctorUpdate(DoctorRegistrationRequest doctorRegistrationRequest, int id);
   Doctor doctorUpdate(DoctorRegistrationRequest doctorRegistrationRequest);
	
	String findByUsernameAndPassword(String username, String password);

	List<Doctor> findAll();

	List<Doctor> findbyDoctorname(String username);

	String doctorUsername(String username);

	Object getByUsername(String username);
}

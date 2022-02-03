package com.pet.clinic.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pet.clinic.entity.OwnerEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pet.clinic.dto.DoctorRegistrationRequest;
import com.pet.clinic.entity.Doctor;
import com.pet.clinic.exception.UnAppropriateAddressException;
import com.pet.clinic.exception.UnAppropriateDesignationException;
import com.pet.clinic.exception.UnAppropriateMobileNumberException;
import com.pet.clinic.exception.UnAppropriateNameException;
import com.pet.clinic.exception.UnAppropriateQualificationException;
import com.pet.clinic.repository.DoctorRepo;

@Service
//This class contain main business logic
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	DoctorRepo doctorRepo;
	Doctor doctor = new Doctor();
	private static Logger logger = LogManager.getLogger(DoctorServiceImpl.class);


	DoctorRepo doctorRepo1;

	//PasswordEncoder passwordEncoder;

	public DoctorServiceImpl(DoctorRepo doctorRepo1) {
		this.doctorRepo1 = doctorRepo1;
		//this.passwordEncoder = passwordEncoder;
	}


	//This method insert the doctor detail in doctor_registration_request table in database
	@Override
	public Doctor insertDoctor(DoctorRegistrationRequest doctorRegistrationRequest) {
		logger.debug("insert method in doctor ServiceImpl");
		doctor.setId(doctorRegistrationRequest.getId());
		String name = doctorRegistrationRequest.getName();
		// This is required pattern to take doctor's name as input
		Pattern ptrnName = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
		// the matcher() method creates a matcher that will match the given input
		// against this pattern
		Matcher matchName = ptrnName.matcher(name);
		if (matchName.find() && matchName.group().equals(name))
			doctor.setName(name);
		else
			throw new UnAppropriateNameException("you have entered incorrect name");
		String designation = doctorRegistrationRequest.getDesignation();
		// This is required pattern to take doctor's designation as input
		Pattern ptrnDesignation = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
		// the matcher() method creates a matcher that will match the given input
		// against this pattern
		Matcher matchDesignation = ptrnDesignation.matcher(designation);
		if (matchDesignation.find() && matchDesignation.group().equals(designation))
			doctor.setDesignation(designation);
		else
			throw new UnAppropriateDesignationException("You have entered incorrect designation");
		String qualification = doctorRegistrationRequest.getQualification();
		// This is required pattern to take doctor's qualification as input
		Pattern ptrnQualification = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
		// the matcher() method creates a matcher that will match the given input
		// against this pattern
		Matcher matchQualification = ptrnQualification.matcher(qualification);
		if (matchQualification.find() && matchQualification.group().equals(qualification))
			doctor.setQualification(qualification);
		else
			throw new UnAppropriateQualificationException("you have entered incorrect qualification");
		String address = doctorRegistrationRequest.getAddress();
		// This is required pattern to take doctor's address as input
		Pattern ptrnAddress = Pattern.compile("^[a-zA-Z][a-zA-Z0-9 ]*$");
		// the matcher() method creates a matcher that will match the given input
		// against this pattern
		Matcher matchAddress = ptrnAddress.matcher(address);
		if (matchAddress.find() && matchAddress.group().equals(address))
			doctor.setAddress(address);
		else
			throw new UnAppropriateAddressException("you have entered incorrect address");
		String moNo = doctorRegistrationRequest.getMobileNumber();
		// (0/91): number starts with (0/91)
		// [7-9]: starting of the number may contain a digit between 7 to 9
		// [0-9]: then contains digits 0 to 9
		Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
		// the matcher() method creates a matcher that will match the given input
		// against this pattern
		Matcher match = ptrn.matcher(moNo);
		if (match.find() && match.group().equals(moNo))
			doctor.setMobileNumber(moNo);
		else
			throw new UnAppropriateMobileNumberException("Incorrect mobile number");


		//doctor.setPassword(this.passwordEncoder.encode(doctorRegistrationRequest.getPassword()));
		String password=doctorRegistrationRequest.getPassword();
		Base64.Encoder encoder=Base64.getEncoder();
		String epassword=encoder.encodeToString(password.getBytes());
		doctor.setPassword(epassword);
		doctor.setUsername(doctorRegistrationRequest.getUsername());

		doctor.setDelete(false);
		doctorRepo.save(doctor);
		logger.debug("Doctor record is: "+doctor);
		return doctor;
	}


//This method fetch a record by doctorId from database table
	@Override
	public Doctor doctorById(int id) {
		logger.error("Entered into get method of serviceImpl "+ id);
		return doctorRepo.findById(id).orElse(doctor);
	}

//This method delete the record by DoctorId from database table
	@Override
	public String doctorDeleteById(int id) {
		logger.debug("Delete method in doctor ServiceImpl");
		Doctor doc = doctorRepo.findById(id).orElse(doctor);
		if (doc.isDelete() == false) {
			doc.setDelete(true);
			doctorRepo.save(doc);
		}
		logger.debug("isDelete status is: "+doc.isDelete());
		return "record deleted successfully";
	}

//This method update the record if required
	@Override
	public Doctor doctorUpdate(DoctorRegistrationRequest doctorRegistrationRequest, int id) {
		logger.debug("PartialUpadate method in doctor ServiceImpl");
		doctor = doctorRepo.findById(id).orElse(doctor);
		if (doctorRegistrationRequest.getId() != 0)
			doctor.setId(doctorRegistrationRequest.getId());
		String name = doctorRegistrationRequest.getName();
		if (name != null) {
			Pattern ptrnName = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
			Matcher matchName = ptrnName.matcher(name);
			if (matchName.find() && matchName.group().equals(name))
				doctor.setName(name);

			else
				throw new UnAppropriateNameException("you have entered incorrect name");
		}

		String designation = doctorRegistrationRequest.getDesignation();
		if (designation != null) {
			Pattern ptrnDesignation = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
			Matcher matchDesignation = ptrnDesignation.matcher(designation);
			if (matchDesignation.find() && matchDesignation.group().equals(designation))
				doctor.setDesignation(designation);

			else
				throw new UnAppropriateDesignationException("You have entered incorrect designation");
		}

		String qualification = doctorRegistrationRequest.getQualification();
		if (qualification != null) {
			Pattern ptrnQualification = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
			Matcher matchQualification = ptrnQualification.matcher(qualification);
			if (matchQualification.find() && matchQualification.group().equals(qualification))
				doctor.setQualification(qualification);
			else
				throw new UnAppropriateQualificationException("you have entered incorrect qualification");
		}
		String address = doctorRegistrationRequest.getAddress();
		if (address != null) {
			Pattern ptrnAddress = Pattern.compile("^[a-zA-Z][a-zA-Z0-9 ]*$");
			Matcher matchAddress = ptrnAddress.matcher(address);
			if (matchAddress.find() && matchAddress.group().equals(address))
				doctor.setAddress(address);
			else
				throw new UnAppropriateAddressException("you have entered incorrect address");
		}
		String moNo = doctorRegistrationRequest.getMobileNumber();
		if (moNo != null) {
			Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
			Matcher match = ptrn.matcher(moNo);
			if (match.find() && match.group().equals(moNo))
				doctor.setMobileNumber(moNo);
			else
				throw new UnAppropriateMobileNumberException("Incorrect mobile number");
		}
		logger.debug("updated doctor  is: "+doctor);
		return doctorRepo.save(doctor);

	}
	
	
	//This method used for validation purpose
	@Override
	public String findByUsernameAndPassword(String username, String password) {

		Doctor doctor=doctorRepo.findByDoctorUserName(username);
		String pass=password;
		String newPass = doctor.getPassword();
		Base64.Decoder decoder=Base64.getDecoder();
		byte[] bytes=decoder.decode(newPass);
		byte[] byteArrray = password.getBytes();
		boolean b =  Arrays.equals(bytes, byteArrray);
		System.out.println(b);

		if(b)
		{
			return "Doctor login Successfully";
		}
		else
		{
			return "User not available";
		}
	}

	//This method findAll record from database
	@Override
	public List<Doctor> findAll() {
		logger.debug("findAll method in doctor ServiceImpl");
		// TODO Auto-generated method stub
		return doctorRepo.findAllUndeletedRecords();
	}
	@Override
	public List<Doctor> findbyDoctorname(String username) {
		return doctorRepo.findbyDoctorname(username);
	}

	@Override
	public String doctorUsername(String username) {
		return doctorRepo.findDoctorUsername(username);
	}

	@Override
	public Object getByUsername(String username) {
		return doctorRepo.findByDoctorUserName(username);
	}


	//This method update record into database
	@Override
	public Doctor doctorUpdate(DoctorRegistrationRequest doctorRegistrationRequest) {
		logger.debug("fullUpdate  method in doctor ServiceImpl");
		doctor.setId(doctorRegistrationRequest.getId());
		doctor.setName(doctorRegistrationRequest.getName());
		doctor.setDesignation(doctorRegistrationRequest.getDesignation());
		doctor.setAddress(doctorRegistrationRequest.getAddress());
		doctor.setMobileNumber(doctorRegistrationRequest.getMobileNumber());
		doctor.setQualification(doctorRegistrationRequest.getQualification());
		doctor.setUsername(doctorRegistrationRequest.getUsername());
		doctor.setPassword(doctorRegistrationRequest.getPassword());
		doctor.setDelete(false);
		return doctorRepo.save(doctor);
	}

	

}
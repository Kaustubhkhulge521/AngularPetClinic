package com.pet.clinic.service;

import java.util.*;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pet.clinic.dto.OwnerRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pet.clinic.entity.OwnerEntity;
import com.pet.clinic.exception.UnAppropriateEmailException;
import com.pet.clinic.exception.UnAppropriateFirstNameException;
import com.pet.clinic.exception.UnAppropriateLastNameException;
import com.pet.clinic.exception.UnAppropriateMobileNumberException;
import com.pet.clinic.exception.UnAppropriateNoteException;
import com.pet.clinic.repository.OwnerRepository;

//This class provide implementation for all the required method.
@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	OwnerRepository ownerRepository;

	//@Autowired
	//PasswordEncoder passwordEncoder;

	private static Logger logger = LogManager.getLogger(OwnerServiceImpl.class);

	// This method will insert data in owner table
	@Override
	public OwnerEntity insertOwner(OwnerRequest ownerRequest) {
		OwnerEntity owner = new OwnerEntity();

		String ownerName = ownerRequest.getOwnerName();
		Pattern pttrn = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
		Matcher matche = pttrn.matcher(ownerName);
		if (matche.find() && matche.group().equals(ownerName)) {
			owner.setOwnerName(ownerName);
		} else
			throw new UnAppropriateFirstNameException(" ownerName is incorrect ");

//		String lastName = ownerRequest.getLastName();
//		Pattern ptt = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
//		Matcher matc = ptt.matcher(lastName);
//		if (matc.find() && matc.group().equals(lastName)) {
//			owner.setLastName(lastName);
//		} else
//			throw new UnAppropriateLastNameException(" lastName is incorrect ");

		String email = ownerRequest.getEmail();
		Pattern pttern = Pattern.compile("^[a-z][a-z][0-9@a-z.]*$");
		Matcher matchers = pttern.matcher(email);
		if (matchers.find() && matchers.group().equals(email)) {
			owner.setEmail(email);
		} else
			throw new UnAppropriateEmailException(" email is incorrect ");

		String mob = ownerRequest.getMobileNumber();
		Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
		Matcher match = ptrn.matcher(mob);

		if (match.find() && match.group().equals(mob)) {
			owner.setMobileNumber(mob);
		} else
			throw new UnAppropriateMobileNumberException("Invalide mobile number");

		String note = ownerRequest.getNote();
		Pattern pt = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
		Matcher mat = pt.matcher(note);
		if (mat.find() && mat.group().equals(note)) {
			owner.setNote(note);
		} else
			throw new UnAppropriateNoteException(" note is incorrect ");

		owner.setDelete(false);
		owner.setUsername(ownerRequest.getUsername());
		//String pass = ownerRequest.getPassword();
		//owner.setPassword(this.passwordEncoder.encode(ownerRequest.getPassword()));
		//owner.setPassword(ownerRequest.getPassword());
		String password=ownerRequest.getPassword();
		Encoder encoder=Base64.getEncoder();
		String epassword=encoder.encodeToString(password.getBytes());
		owner.setPassword(epassword);
		ownerRepository.save(owner);
		logger.debug("owner record is: "+ owner);
		return owner;
	}

	// This method will delete record for particular id
	@Override
	public String deleteById(int id) {
		OwnerEntity owner = new OwnerEntity();

		owner = ownerRepository.getById(id);
		if (owner.isDelete() == false) {
			owner.setDelete(true);
			ownerRepository.save(owner);
		}
		logger.debug("Owner id is: "+ id);
		return "Owner deleted successfully";
	}

	// This method will provide particular data from owner table
	@Override
	public Optional<OwnerEntity> ownerById(int id) {
		Optional<OwnerEntity> ownerEntity = ownerRepository.findById(id);
		logger.debug("Owner  id: "+ id);
		return ownerEntity;
	}

	// This method will update record into owner table
	@Override
	public OwnerEntity ownerUpdate(OwnerRequest ownerRequest, int id) {
		OwnerEntity owner = new OwnerEntity();

		owner = ownerRepository.getById(id);
		if (ownerRequest.getId() != 0)
			owner.setId(ownerRequest.getId());

		String ownerName = ownerRequest.getOwnerName();
		if (ownerName != null) {
			Pattern pttrn = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
			Matcher matche = pttrn.matcher(ownerName);
			if (matche.find() && matche.group().equals(ownerName))
				owner.setOwnerName(ownerName);
		else
			throw new UnAppropriateFirstNameException(" firstName is incorrect ");
		}
//		String lastName = ownerRequest.getLastName();
//		if (lastName != null) {
//			Pattern ptt = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
//			Matcher matc = ptt.matcher(lastName);
//			if (matc.find() && matc.group().equals(lastName))
//				owner.setLastName(lastName);
//		 else
//			throw new UnAppropriateLastNameException(" lastName is incorrect ");
//		}
		String email = ownerRequest.getEmail();
		if (email != null) {
			Pattern pttern = Pattern.compile("^[a-z][a-z][0-9@a-z.]*$");
			Matcher matchers = pttern.matcher(email);
			if (matchers.find() && matchers.group().equals(email))
				owner.setEmail(email);
		 else
			throw new UnAppropriateEmailException(" email is incorrect ");
		}
		String mob = ownerRequest.getMobileNumber();
		if (mob != null) {
			Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
			Matcher match = ptrn.matcher(mob);

			if (match.find() && match.group().equals(mob))
				owner.setMobileNumber(mob);
		 else
			throw new UnAppropriateMobileNumberException("Invalide mobile number");
		}
		String note = ownerRequest.getNote();
		if (note != null) {
			Pattern pt = Pattern.compile("^[a-zA-Z][a-zA-Z ]*$");
			Matcher mat = pt.matcher(note);
			if (mat.find() && mat.group().equals(note))
				owner.setNote(note);
		else
			throw new UnAppropriateNoteException(" note is incorrect ");
		}
		owner.setDelete(false);
		owner = ownerRepository.save(owner);
        logger.debug("Updated owner is: "+ owner);
		return owner;
	}

	// this method used for the validation purpose
	@Override
	public String findByUsernameAndPassword(String username, String password) {
		OwnerEntity ownerEntity=ownerRepository.findByUsername1(username);
		String newPass = ownerEntity.getPassword();
		Decoder decoder=Base64.getDecoder();
		byte[] bytes=decoder.decode(newPass);
		byte[] byteArrray = password.getBytes();
		boolean b =  Arrays.equals(bytes, byteArrray);
        System.out.println(b);
		if(b){

			return "Owner login Successfully";
		}
		else
		{

			return "User not available";
		}
	}
	
	//this method provide implementation for fetch all record from database
	@Override
	public List<OwnerEntity> findAll() {
		// TODO Auto-generated method stub
		return ownerRepository.findAllUndeletedRecords();
	}

	//this method provides implementation for update record
	@Override
	public OwnerEntity updateOwner(OwnerRequest ownerRequest) {
		OwnerEntity owner = new OwnerEntity();
		owner.setId(ownerRequest.getId());
		owner.setOwnerName(ownerRequest.getOwnerName());
		//owner.setLastName(ownerRequest.getLastName());
		owner.setEmail(ownerRequest.getEmail());
		owner.setMobileNumber(ownerRequest.getMobileNumber());
		owner.setNote(ownerRequest.getNote());
		owner.setUsername(ownerRequest.getUsername());
		owner.setPassword(ownerRequest.getPassword());
		owner.setDelete(false);
		owner = ownerRepository.save(owner);
		logger.debug("Updated owner is: "+owner);
		return owner;
	}



	//this method used to delete record from database
	@Override
	public void deleteOwner(int id) {
		logger.debug("Owner id is : "+id);
		ownerRepository.deleteById(id);
	}

	@Override
	public List<OwnerEntity> findOwner(String username) {
		return ownerRepository.findownerByUserName(username);
	}

//	@Override
//	public String ownerUserName(String username) {
//		return ownerRepository.selectUserName(username);
//	}

	@Override
	public Object getByUsername(String user) {
		System.out.println(user);
		OwnerEntity ownerEntity = new OwnerEntity();
		ownerEntity = ownerRepository.findByUsername(user).get();
		System.out.println(ownerEntity);
		return ownerEntity;
	}

	@Override
	public Object getByEmail(String email) {
		return ownerRepository.findByEmail(email);
	}
//	@Override
//	public String ownerEmail(String email) {
//
//		return ownerRepository.selectEmail(email);
//	}

	@Override
	public List<OwnerEntity> findByUsername(String username) {

		return ownerRepository.findByUserName(username);
	}


}

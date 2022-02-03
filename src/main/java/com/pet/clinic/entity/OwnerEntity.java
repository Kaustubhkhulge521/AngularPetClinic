package com.pet.clinic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitiaizer" })
@Entity(name = "owner")
public class OwnerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
//	private String firstName;
//	private String lastName;
	private String ownerName;
	private String email;
	private String mobileNumber;
	private String note;
	private boolean isDelete;
	private String username;
	private String password;

}
package com.pet.clinic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OwnerRequest {
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
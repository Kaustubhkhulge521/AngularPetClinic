package com.pet.clinic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PetRequest {
	
	private int petId;
	private String petName;
	private String username;
	private int speciesId;
	private String petSpecies;
	private String petSymptoms;
	private boolean isDelete;

}
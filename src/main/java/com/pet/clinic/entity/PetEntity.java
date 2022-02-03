package com.pet.clinic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "Pet")
public class PetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "petId")
	private int petId;

	@Column(name = "PetName")
	private String petName;

	@Column(name = "username")
	private String username;

	@Column(name = "species_id")
	private int speciesId;

	@Column(name = "petSpecies")
	private String petSpecies;

	@Column(name = "petSymptoms")
	private String petSymptoms;

	@NotNull
	@Column(name = "delete_Status")
	private boolean isDelete;


}
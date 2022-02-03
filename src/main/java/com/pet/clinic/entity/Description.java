package com.pet.clinic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Description {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int petId;
	@Column(name = "petName")
	private String petName;
	private int speciesId;
	private String petBreed;
	private String petDisease;
	private String medicine;
	private String medicineDose;
	@Column(name = "deleteStatus")
	private boolean isDelete;

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public int getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(int speciesId) {
		this.speciesId = speciesId;
	}

	public String getPetBreed() {
		return petBreed;
	}

	public void setPetBreed(String petBreed) {
		this.petBreed = petBreed;
	}

	public String getPetDisease() {
		return petDisease;
	}

	public void setPetDisease(String petDisease) {
		this.petDisease = petDisease;
	}

	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getMedicineDose() {
		return medicineDose;
	}

	public void setMedicineDose(String medicineDose) {
		this.medicineDose = medicineDose;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "Description [petId=" + petId + ", petName=" + petName + ", speciesId=" + speciesId + ", petBreed="
				+ petBreed + ", petDisease=" + petDisease + ", medicine=" + medicine + ", medicineDose=" + medicineDose
				+ ", isDelete=" + isDelete + "]";
	}

	}

package com.pet.clinic.dto;

public class DescriptionRequest {

	private int petId;
	private String petName;
	private int speciesId;
	private String petBreed;
	private String petDisease;
	private String medicine;
	private String medicineDose;
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

	public void setDelete(boolean b) {

	}

	@Override
	public String toString() {
		return "DescriptionRequest [petId=" + petId + ", petName=" + petName + ", speciesId=" + speciesId
				+ ", petBreed=" + petBreed + ", petDisease=" + petDisease + ", medicine=" + medicine + ", medicineDose="
				+ medicineDose + ", isDelete=" + isDelete + "]";
	}

}
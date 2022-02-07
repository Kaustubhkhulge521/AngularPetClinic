package com.pet.clinic.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class OwnerPet {

    private String petName;
    private String username;
    private int speciesId;
    private String petSpecies;
    private String petSymptoms;
    private boolean isDelete;
    private String ownerName;
    private String email;
    private String mobileNumber;
    private String note;
    private String password;


}

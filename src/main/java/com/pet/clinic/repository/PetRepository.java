package com.pet.clinic.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pet.clinic.entity.PetEntity;

//This interface extends JpaRepository which has some predefined methods for basic crude operations.
@Repository
public interface PetRepository extends JpaRepository<PetEntity, Integer> {

List<PetEntity> findByisDelete(Boolean b);

//@Query(value = "select *from public.pet where pet.username= ?1", nativeQuery = true)
    @Query(value = "select *from public.pet where pet.delete_status='false' and pet.username=?1", nativeQuery = true)
List<PetEntity> findByusername(String username);


    @Query(value = "SELECT o.email, o.mobile_number, o.note, o.owner_name, o.username as owner_username, p.pet_name, p.pet_species, p.pet_symptoms, p.species_id,p.username as pet_username FROM public.owner o INNER JOIN public.pet p ON p.pet_id = o.id;", nativeQuery = true)
    List<Map<String,String>>  findAllDataFromPetAndOwner();


}
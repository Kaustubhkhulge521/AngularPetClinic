package com.pet.clinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pet.clinic.entity.PetEntity;

//This interface extends JpaRepository which has some predefined methods for basic crude operations.
@Repository
public interface PetRepository extends JpaRepository<PetEntity, Integer> {

List<PetEntity> findByisDelete(Boolean b);

@Query(value = "select *from public.pet where pet.username= ?1", nativeQuery = true)
List<PetEntity> findByusername(String username);


}
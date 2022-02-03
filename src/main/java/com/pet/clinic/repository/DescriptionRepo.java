package com.pet.clinic.repository;
import com.pet.clinic.entity.Description;
import com.pet.clinic.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//This interface extends JpaRepository which has some predefined method for basic crude operation.
public interface DescriptionRepo extends JpaRepository<Description, Integer> {

	static void save(Description partialUpdate, String id) {

	}

	@Query(value ="select *from public.description where description.delete_status='false'", nativeQuery = true)
	public List<Description> findAllUndeletedRecords();

}
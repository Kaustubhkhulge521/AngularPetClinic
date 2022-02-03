package com.pet.clinic.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pet.clinic.entity.OwnerEntity;

//This interface extends JpaRepository which has some predefined method for basic crude operations.
@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, Integer> {
	OwnerEntity owner = new OwnerEntity();

	@Query(value = "select *from public.owner where owner.username=?1",nativeQuery = true)
	List<OwnerEntity> findownerByUserName(String username);


	@Query(value = "select *from public.owner where owner.username=?1", nativeQuery = true)
	List<OwnerEntity> findByUserName(String username);

	@Query(value = "select *from public.owner where owner.username=?1", nativeQuery = true)
	OwnerEntity findByUsername1(String username);

	@Query(value ="select *from public.owner where owner.is_delete='false'", nativeQuery = true)
	List<OwnerEntity> findAllUndeletedRecords();

	Optional<OwnerEntity> findByUsername(String username);

//	@Query(value = "select owner.email from public.owner where owner.email=?1" , nativeQuery = true)
//	String selectEmail(String email);

	Optional<OwnerEntity> findByEmail(String email);
}

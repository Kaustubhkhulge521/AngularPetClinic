package com.pet.clinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pet.clinic.entity.Doctor;

//This interface extends to JPARepository Interface which has some predefine method in it for basic curd operations
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
	
	//@Query(value = "SELECT d FROM Doctor d WHERE d.username = :username", nativeQuery = true)
	  public Doctor findByUsername(@Param("username") String username);


	@Query(value = "select *from public.doctor where doctor.username=?1", nativeQuery = true)
	   Doctor findByDoctorUserName(String username);


	  //@Query(value = "SELECT d FROM Doctor d WHERE d.username = :username and d.password= :password", nativeQuery = true)
	public Doctor findByUsernameAndPassword(String username, String password);

	@Query(value ="select *from public.doctor where doctor.id='false'", nativeQuery = true)
   public List<Doctor> findAllUndeletedRecords();
	 

	
	  @Query(value = "SELECT d FROM Doctor d WHERE d.id = 1", nativeQuery = true)
	  public Doctor findByEmail(String email);

	Doctor doctor = new Doctor();
	@Query(value = "SELECT * FROM PUBLIC.doctor WHERE doctor.username=?1", nativeQuery = true)
	List<Doctor> findbyDoctorname(String username);


	@Query(value = "select doctor.username from public.doctor where doctor.username=?1",nativeQuery = true)
	public String findDoctorUsername(String username);

}

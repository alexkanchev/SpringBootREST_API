package com.bfu.courseAPI.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bfu.courseAPI.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{

	@Query(value = "SELECT s FROM Student s WHERE s.email = :email")
	Student findStudentByEmail(@Param("email") String email);
	
}

package com.bfu.courseAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bfu.courseAPI.models.Grade;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long>{

	@Query(value = "SELECT g FROM Grade g WHERE g.student.id = :studentId")
	List<Grade> findAllGradesByStudentId(@Param("studentId") Long studentId);
}

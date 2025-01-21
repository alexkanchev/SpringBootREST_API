package com.bfu.courseAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bfu.courseAPI.models.Clas;
import com.bfu.courseAPI.models.Generation;

@Repository
public interface GenerationRepository extends CrudRepository<Generation, Long>{

	@Query(value = "SELECT g FROM Generation g WHERE g.student.id = :studentId AND g.clas.id = :clasId")
	Generation findGenerationByStudentIdAndClasId(@Param("studentId") Long studentId, @Param("clasId") Long clasId);
	
	@Query(value = "SELECT g.clas FROM Generation g WHERE g.student.id = :studentId AND g.clas.validTo IS NULL")
	List<Clas> getAllClasByStudentId(@Param("studentId") Long studentId);
	
	@Modifying
	@Query(value = "DELETE FROM Generation g WHERE g.student.id = :studentId")
	static
	void deleteAllGenerationsByStudentId(@Param("studentId") Long studentId) {
		// TODO Auto-generated method stub
		
	}
}

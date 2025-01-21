package com.bfu.courseAPI.services;

import java.util.List;

import com.bfu.courseAPI.models.Grade;
import com.bfu.courseAPI.views.GradeForm;

public interface GradeService {
	
	Grade createGrade(GradeForm form);
	
	Grade updateGrade(GradeForm form);
	
	void deleteGradeById(Long id);

	Grade getGradeById(Long id);
	
	List<Grade> getGradesByStudentId(Long studentId);
	
}

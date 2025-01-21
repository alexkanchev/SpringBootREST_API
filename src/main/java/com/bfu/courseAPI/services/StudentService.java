package com.bfu.courseAPI.services;

import com.bfu.courseAPI.models.Student;
import com.bfu.courseAPI.views.StudentForm;
import com.bfu.courseAPI.views.StudentView;

public interface StudentService {

	Student getStudentById(Long id);
	
	Student getStudentByEmail(String email);
	
	Student createStudent(StudentForm form);
	
	Student updateStudent(StudentForm form);
	
	void deleteStudentById(Long id);
	
	StudentView getStudentViewById(Long id);
}

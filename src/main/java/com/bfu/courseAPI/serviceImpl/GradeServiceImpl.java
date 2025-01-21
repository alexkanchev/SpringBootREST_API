package com.bfu.courseAPI.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfu.courseAPI.models.Grade;
import com.bfu.courseAPI.repositories.GradeRepository;
import com.bfu.courseAPI.services.GradeService;
import com.bfu.courseAPI.services.StudentService;
import com.bfu.courseAPI.views.GradeForm;

@Service
@Transactional
public class GradeServiceImpl implements GradeService{
	
	private final GradeRepository gradeRepository;
	private final StudentService studentService;
	
	@Autowired
	public GradeServiceImpl(GradeRepository gradeRepository, StudentService studentService) {
		this.gradeRepository = gradeRepository;
		this.studentService = studentService;
	}

	@Override
	public Grade createGrade(GradeForm form) {
		Grade grade = formToGrade(form, new Grade(), false);
		return gradeRepository.save(grade);
	}

	@Override
	public Grade updateGrade(GradeForm form) {
		Grade grade = getGradeById(form.getId());
		grade = formToGrade(form, grade, true);
		return gradeRepository.save(grade);
	}

	@Override
	public void deleteGradeById(Long id) {
		Grade grade = getGradeById(id);
		gradeRepository.deleteById(grade.getId());
	}

	@Override
	public Grade getGradeById(Long id) {
		Optional<Grade> grade = gradeRepository.findById(id);
		return grade
				.orElseThrow(() -> new IllegalArgumentException("Grade not found!"));
	}

	@Override
	public List<Grade> getGradesByStudentId(Long studentId) {
		return gradeRepository.findAllGradesByStudentId(studentId);
	}

	private Grade formToGrade(GradeForm form, Grade grade, Boolean edit) {
		grade.setGrade(form.getGrade());
		grade.setNotes(form.getNotes());
		if(!edit) {
			grade.setDateAdded(new Timestamp(new Date().getTime()));
			grade.setStudent(studentService.getStudentById(form.getStudentId()));
		}
		return grade;
	}
}

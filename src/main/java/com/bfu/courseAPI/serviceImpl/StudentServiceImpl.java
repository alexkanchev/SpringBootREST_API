package com.bfu.courseAPI.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfu.courseAPI.models.Clas;
import com.bfu.courseAPI.models.Grade;
import com.bfu.courseAPI.models.Student;
import com.bfu.courseAPI.models.Generation;
import com.bfu.courseAPI.repositories.GradeRepository;
import com.bfu.courseAPI.repositories.GenerationRepository;
import com.bfu.courseAPI.repositories.StudentRepository;
import com.bfu.courseAPI.services.ClasService;
import com.bfu.courseAPI.services.StudentService;
import com.bfu.courseAPI.views.StudentForm;
import com.bfu.courseAPI.views.StudentView;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
	
	private final StudentRepository studentRepository;
	private final GradeRepository gradeRepository;
	private final GenerationRepository generationRepository;
	private final ClasService clasService;
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, GradeRepository gradeRepository, GenerationRepository generationRepository, ClasService clasService) {
		this.studentRepository = studentRepository;
		this.gradeRepository = gradeRepository;
		this.generationRepository = generationRepository;
		this.clasService = clasService;
	}

	@Override
	public Student getStudentById(Long id) {
		Optional<Student> student = studentRepository.findById(id);
		return student
				.orElseThrow(() -> new IllegalArgumentException("Student not found!"));
	}

	@Override
	public Student getStudentByEmail(String email) {
		return studentRepository.findStudentByEmail(email);
	}

	@Override
	public Student createStudent(StudentForm form) {
		Student student = formToStudent(form, new Student(), false);
		student = studentRepository.save(student);
		addStudentToGroups(student, form.getClasIds(), false);
		return student;
	}

	@Override
	public Student updateStudent(StudentForm form) {
		Student student = getStudentById(form.getId());
		student = formToStudent(form, student, true);
		student = studentRepository.save(student);
		addStudentToGroups(student, form.getClasIds(), true);
		return student;
	}

	@Override
	public void deleteStudentById(Long id) {
		Student student = getStudentById(id);
		studentRepository.deleteById(student.getId());
	}

	@Override
	public StudentView getStudentViewById(Long id) {
		StudentView view = new StudentView();
		Student student = getStudentById(id);
		view.setId(student.getId());
		view.setName(student.getName());
		view.setEmail(student.getEmail());
		List<Grade> grades = gradeRepository.findAllGradesByStudentId(id);
		view.setGrades(grades.stream().map(e -> e.getGrade()).collect(Collectors.toList()));
		List<Clas> classes = generationRepository.getAllClasByStudentId(id);
		view.setClasNames(classes.stream().map(e -> e.getName()).collect(Collectors.toList()));
		return view;
	}

	private Student formToStudent(StudentForm form, Student student, Boolean edit) {
		student.setName(form.getName());
		student.setEmail(form.getEmail());
		if(!edit) {
			student.setDateAdded(new Timestamp(new Date().getTime()));
		}
		return student;
	}
	
	private void addStudentToGroups(Student student, List<Long> clasIds, Boolean edit) {
		if(edit) {
			GenerationRepository.deleteAllGenerationsByStudentId(student.getId());
		}
		if(clasIds != null && clasIds.size() > 0) {
			for(Long id : clasIds) {
				Generation studentGroup = new Generation();
				studentGroup.setStudent(student);
				studentGroup.setClas(clasService.getClasById(id));
				studentGroup.setDateAdded(new Timestamp(new Date().getTime()));
				generationRepository.save(studentGroup);
			}
		}
	}
}

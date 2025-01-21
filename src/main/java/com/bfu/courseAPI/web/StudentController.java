package com.bfu.courseAPI.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bfu.courseAPI.models.Student;
import com.bfu.courseAPI.services.StudentService;
import com.bfu.courseAPI.views.StudentEmailForm;
import com.bfu.courseAPI.views.StudentForm;
import com.bfu.courseAPI.views.StudentView;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long id){
		Student student = studentService.getStudentById(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@PostMapping("email")
	public ResponseEntity<Student> getStudentByEmail(@Valid @RequestBody StudentEmailForm form){
		Student student = studentService.getStudentByEmail(form.getEmail());
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@GetMapping("view/{id}")
	public ResponseEntity<StudentView> getStudentViewById(@PathVariable(value = "id") Long id){
		StudentView view = studentService.getStudentViewById(id);
		return new ResponseEntity<>(view, HttpStatus.OK);
	}
	
	@PostMapping("create")
	@Transactional(rollbackFor = {RuntimeException.class})
	public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentForm studentForm){
		Student student = studentService.createStudent(studentForm);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}
	
	@PutMapping("update")
	@Transactional(rollbackFor = {RuntimeException.class})
	public ResponseEntity<Student> updateUser(@Valid @RequestBody StudentForm studentForm){
		Student student = studentService.updateStudent(studentForm);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("delete/{id}")
	@Transactional(rollbackFor = {RuntimeException.class})
	public void deleteStudent(@PathVariable(value = "id") Long id) {
		studentService.deleteStudentById(id);
	}
}

package com.bfu.courseAPI.web;

import java.util.List;

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

import com.bfu.courseAPI.models.Grade;
import com.bfu.courseAPI.services.GradeService;
import com.bfu.courseAPI.views.GradeForm;

@RestController
@RequestMapping(value = "/grade")
public class GradeController {

	private final GradeService gradeService;
	
	@Autowired
	public GradeController(GradeService gradeService) {
		this.gradeService = gradeService;
	}
	
	@GetMapping("studentId/{studentId}")
	public ResponseEntity<List<Grade>> getGradesByStudentId(@PathVariable(value = "studentId") Long studentId){
		List<Grade> grades = gradeService.getGradesByStudentId(studentId);
		if(grades == null || grades.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(grades, HttpStatus.OK);
		}
	}
	
	@PostMapping("create")
	@Transactional(rollbackFor = {RuntimeException.class})
	public ResponseEntity<Grade> createGrade(@Valid @RequestBody GradeForm gradeForm){
		Grade grade = gradeService.createGrade(gradeForm);
		return new ResponseEntity<>(grade, HttpStatus.CREATED);
	}
	
	@PutMapping("update")
	@Transactional(rollbackFor = {RuntimeException.class})
	public ResponseEntity<Grade> updateGrade(@Valid @RequestBody GradeForm gradeForm){
		Grade grade = gradeService.updateGrade(gradeForm);
		return new ResponseEntity<>(grade, HttpStatus.CREATED);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("delete/{id}")
	@Transactional(rollbackFor = {RuntimeException.class})
	public void deleteGrade(@PathVariable(value = "id") Long id) {
		gradeService.deleteGradeById(id);
	}
}

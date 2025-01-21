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

import com.bfu.courseAPI.models.Clas;
import com.bfu.courseAPI.services.ClasService;
import com.bfu.courseAPI.views.ClasForm;

@RestController
@RequestMapping(value = "/clas")
public class ClasController {
	
	private final ClasService clasService;
	
	@Autowired
	public ClasController(ClasService clasService) {
		this.clasService = clasService;
	}

	@PostMapping("create")
	@Transactional(rollbackFor = {RuntimeException.class})
	public ResponseEntity<Clas> createGroup(@Valid @RequestBody ClasForm clasForm){
		Clas clas = clasService.createClas(clasForm);
		return new ResponseEntity<>(clas, HttpStatus.CREATED);
	}
	
	@PutMapping("update")
	@Transactional(rollbackFor = {RuntimeException.class})
	public ResponseEntity<Clas> updateGroup(@Valid @RequestBody ClasForm clasForm){
		Clas clas = clasService.updateClas(clasForm);
		return new ResponseEntity<>(clas, HttpStatus.CREATED);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("delete/{id}")
	@Transactional(rollbackFor = {RuntimeException.class})
	public void deactivateGroup(@PathVariable(value = "id") Long id) {
		clasService.deactivateClasById(id);
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<Clas> getGroupById(@PathVariable(value = "id") Long id){
		Clas clas = clasService.getClasById(id);
		return new ResponseEntity<>(clas, HttpStatus.OK);
	}
}

package com.bfu.courseAPI.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bfu.courseAPI.models.Generation;
import com.bfu.courseAPI.services.GenerationService;
import com.bfu.courseAPI.views.GenerationForm;

@RestController
@RequestMapping(value = "/generation")
public class GenerationController {

	private final GenerationService generationService;
	
	@Autowired
	public GenerationController(GenerationService generationService) {
		this.generationService = generationService;
	}
	
	@PostMapping("create")
	@Transactional(rollbackFor = {RuntimeException.class})
	public ResponseEntity<Generation> createGeneration(@Valid @RequestBody GenerationForm generationForm){
		Generation generation = generationService.createGeneration(generationForm);
		return new ResponseEntity<>(generation, HttpStatus.CREATED);
	}
	
	@PutMapping("update")
	@Transactional(rollbackFor = {RuntimeException.class})
	public ResponseEntity<Generation> updateGeneration(@Valid @RequestBody GenerationForm generationForm){
		Generation generation = generationService.updateGeneration(generationForm);
		return new ResponseEntity<>(generation, HttpStatus.CREATED);
	}
}

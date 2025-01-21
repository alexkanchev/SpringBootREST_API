package com.bfu.courseAPI.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfu.courseAPI.models.Generation;
import com.bfu.courseAPI.repositories.GenerationRepository;
import com.bfu.courseAPI.services.ClasService;
import com.bfu.courseAPI.services.GenerationService;
import com.bfu.courseAPI.services.StudentService;
import com.bfu.courseAPI.views.GenerationForm;

@Service
@Transactional
public class GenerationServiceImpl implements GenerationService{

	private final GenerationRepository generationRepository;
	private final StudentService studentService;
	private final ClasService clasService;
	
	@Autowired
	public GenerationServiceImpl(GenerationRepository generationRepository, StudentService studentService, ClasService clasService) {
		this.generationRepository = generationRepository;
		this.studentService = studentService;
		this.clasService = clasService;
	}

	@Override
	public Generation createGeneration(GenerationForm form) {
		Generation generation = formToGeneration(form, new Generation(), false);
		return generationRepository.save(generation);
	}

	@Override
	public Generation updateGeneration(GenerationForm form) {
		Generation generation = getGenerationByStudentIdAndClasId(form.getPrevStudentId(), form.getPrevClasId());
		generation = formToGeneration(form, generation, true);
		return generationRepository.save(generation);
	}

	@Override
	public Generation getGenerationById(Long id) {
		Optional<Generation> generation = generationRepository.findById(id);
		return generation
				.orElseThrow(() -> new IllegalArgumentException("Generation not found"));
	}

	@Override
	public Generation getGenerationByStudentIdAndClasId(Long studentId, Long clasId) {
		Generation generation = generationRepository.findGenerationByStudentIdAndClasId(studentId, clasId);
		if(generation != null) {
			return generation;
		} else {
			throw new IllegalArgumentException("Generation not found");
		}	
	}

	private Generation formToGeneration(GenerationForm form, Generation generation, Boolean edit) {
		generation.setStudent(studentService.getStudentById(form.getNewStudentId()));
		generation.setClas(clasService.getClasById(form.getNewClasId()));
		if(!edit) {
			generation.setDateAdded(new Timestamp(new Date().getTime()));
		}
		return generation;
	}
}

package com.bfu.courseAPI.services;

import com.bfu.courseAPI.models.Generation;
import com.bfu.courseAPI.views.GenerationForm;

public interface GenerationService {

	Generation createGeneration(GenerationForm form);
	
	Generation updateGeneration(GenerationForm form);
	
	Generation getGenerationById(Long id);
	
	Generation getGenerationByStudentIdAndClasId(Long studentId, Long clasId);
}

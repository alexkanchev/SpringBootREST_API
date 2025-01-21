package com.bfu.courseAPI.services;

import com.bfu.courseAPI.models.Clas;
import com.bfu.courseAPI.views.ClasForm;

public interface ClasService {

	Clas createClas(ClasForm form);
	
	Clas updateClas(ClasForm form);
	
	Clas getClasById(Long id);
	
	void deactivateClasById(Long id);
}

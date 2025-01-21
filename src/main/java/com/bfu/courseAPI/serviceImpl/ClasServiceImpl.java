package com.bfu.courseAPI.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfu.courseAPI.models.Clas;
import com.bfu.courseAPI.repositories.ClasRepository;
import com.bfu.courseAPI.services.ClasService;
import com.bfu.courseAPI.views.ClasForm;

@Service
@Transactional
public class ClasServiceImpl implements ClasService{
	
	private final ClasRepository clasRepository;
	
	@Autowired
	public ClasServiceImpl(ClasRepository clasRepository) {
		this.clasRepository = clasRepository;
	}

	@Override
	public Clas createClas(ClasForm form) {
		Clas clas = formToClas(form, new Clas(), false);
		return clasRepository.save(clas);
	}

	@Override
	public Clas updateClas(ClasForm form) {
		Clas clas = getClasById(form.getId());
		clas = formToClas(form, clas, true);
		return clasRepository.save(clas);
	}

	@Override
	public Clas getClasById(Long id) {
		Optional<Clas> clas = clasRepository.findById(id);
		return clas
				.orElseThrow(() -> new IllegalArgumentException("Class not found"));
	}

	@Override
	public void deactivateClasById(Long id) {
		Clas clas = getClasById(id);
		clas.setValidTo(new Timestamp(new Date().getTime()));
		clasRepository.save(clas);
	}

	private Clas formToClas(ClasForm form, Clas clas, Boolean edit) {
		clas.setName(form.getName());
		clas.setDescription(form.getDescription());
		if(!edit) {
			clas.setValidFrom(new Timestamp(new Date().getTime()));
		}
		return clas;
	}
}

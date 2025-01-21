package com.bfu.courseAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bfu.courseAPI.models.Clas;

@Repository
public interface ClasRepository extends CrudRepository<Clas, Long>{

}

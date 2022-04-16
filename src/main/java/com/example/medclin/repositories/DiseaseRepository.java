package com.example.medclin.repositories;

import com.example.medclin.model.Disease;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiseaseRepository extends CrudRepository<Disease, Integer> {
    List<Disease> findAllByOrderByName();
    Disease findById(int id);
}

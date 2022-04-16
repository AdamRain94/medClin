package com.example.medclin.repositories;

import com.example.medclin.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
    List<Patient> findAllByOrderByFullName();
    Patient findByFullName(String name);
}

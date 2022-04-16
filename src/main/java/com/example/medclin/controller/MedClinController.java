package com.example.medclin.controller;

import com.example.medclin.model.Disease;
import com.example.medclin.model.Patient;
import com.example.medclin.repositories.DiseaseRepository;
import com.example.medclin.repositories.PatientRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class MedClinController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DiseaseRepository diseaseRepository;

    private String fullName;

    @GetMapping("/medclin")
    public String medclin() {
        return "medclin";
    }

    @GetMapping("/medclin/add")
    public String add() {
        return "add";
    }

    @GetMapping("/medclin/search")
    public String search() {
        return "search";
    }

    @GetMapping("/medclin/directory")
    public String directory() {
        return "directory";
    }

    @GetMapping("/medclin/patient")
    public String searchPatient(@RequestParam(value = "name", defaultValue = "name") String name) {
        fullName = name;
        return "patient";
    }

    @PostMapping("/medclin/add/patient")
    public String addPatient(@RequestParam String surname, @RequestParam String name, @RequestParam String patronymic, @RequestParam String passport, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateOfBirth, @RequestParam String sex) {
        Patient patient = new Patient();
        patient.setFullName(surname + " " + name + " " + patronymic);
        patient.setPassport(passport);
        patient.setDateOfBirth(dateOfBirth);
        patient.setSex(sex);
        patientRepository.save(patient);
        return "add";
    }

    @PostMapping("/medclin/add/disease")
    public String addDiseaseToPatient(@RequestParam int id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date installationDate) {
        Patient patient = patientRepository.findByFullName(fullName);
        Disease disease = diseaseRepository.findById(id);
        patient.setDiseases(disease);
        patient.setInstallationDate(installationDate);
        patientRepository.save(patient);
        return "patient";
    }

    @PostMapping("/medclin/delete/disease")
    public String deleteDiseaseToPatient(@RequestParam String name ) {
        //TODO: додлеать метод после добавления многих болезний пользователям
        Patient patient = patientRepository.findByFullName(fullName);
        patient.setDiseases(null);
        patient.setInstallationDate(null);
        patientRepository.save(patient);
        return "patient";
    }

    @PostMapping("/medclin/directory/add")
    public String addDisease(@RequestParam String name, @RequestParam String description) {
        Disease disease = new Disease();
        disease.setName(name);
        disease.setDescription(description);
        diseaseRepository.save(disease);
        return "directory";
    }

    @GetMapping("/medclin/search/addAll")
    @ResponseBody
    public List<Patient> searchAddAll() {
        return patientRepository.findAllByOrderByFullName();
    }

    @GetMapping("/medclin/directory/addAll")
    @ResponseBody
    public List<Disease> directoryAddAll() {
        return diseaseRepository.findAllByOrderByName();
    }

    @GetMapping("/medclin/patient/name")
    @ResponseBody
    public Patient getPatient() {
        return patientRepository.findByFullName(fullName);
    }

}

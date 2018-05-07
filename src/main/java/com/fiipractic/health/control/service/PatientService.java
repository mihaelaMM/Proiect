package com.fiipractic.health.control.service;

import com.fiipractic.health.boundry.exceptions.NotFoundException;
import com.fiipractic.health.entity.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getPatients();

    Patient getPatient(Long id);

    Patient updatePatient(Patient patient) throws NotFoundException;

    void deletePatient(Patient patient);

    Patient savePatient(Patient patient);

  //  List<Patient> findAllPatientWithAge(Long patientAge);

}

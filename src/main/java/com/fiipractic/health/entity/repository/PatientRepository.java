package com.fiipractic.health.entity.repository;

import com.fiipractic.health.entity.model.Doctor;
import com.fiipractic.health.entity.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long>, CrudRepository<Patient, Long> {


}

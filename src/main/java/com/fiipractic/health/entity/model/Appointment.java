package com.fiipractic.health.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiipractic.health.control.service.DoctorService;
import com.fiipractic.health.control.service.DoctorServiceImpl;

import javax.persistence.*;
import javax.print.Doc;
import java.util.Date;

@Entity
@Table(name = "appointment")
@JsonIgnoreProperties(value = { "patient", "doctor"})
public class Appointment {

    @Column
    private Long doctorId;

    @Column
    private Long patientId;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    private String cause;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_key", nullable =  false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_keyh", nullable = false)
    private Patient patient;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) throws IllegalArgumentException{
        if (endTime.after(startTime))
             this.startTime = startTime;

        else
            throw new IllegalArgumentException(String.format("Invalid start time!Start time could be before end time!"));
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) throws IllegalArgumentException {
        if(startTime.before(endTime))
             this.endTime = endTime;
        else
            throw new IllegalArgumentException(String.format("Invalid endTime! End time could be after start time!"));

    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}

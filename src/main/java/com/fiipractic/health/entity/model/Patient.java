package com.fiipractic.health.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
@JsonIgnoreProperties(value = "appointments")

public class Patient extends Person {

    @Column(name="patient_id")
    private String doctorId;

   @Column(name="age", nullable = false)
    private int age;

   @OneToMany(cascade =  CascadeType.ALL, mappedBy = "patient")
   private List <Appointment> appointments = new ArrayList<>();


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}

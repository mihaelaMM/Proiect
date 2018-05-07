package com.fiipractic.health.boundry.mapper;

import com.fiipractic.health.boundry.AppointmentDto;
import com.fiipractic.health.entity.model.Appointment;
import com.fiipractic.health.entity.model.Doctor;
import com.fiipractic.health.entity.model.Patient;

public class ObjectMapper {

    public static void map2DoctorDb(Doctor doctorDb, Doctor doctorRequest){
        doctorDb.setFirstName(doctorRequest.getFirstName());
        doctorDb.setLastName(doctorRequest.getLastName());
        doctorDb.setFunction(doctorRequest.getFunction());
        doctorDb.setId(doctorRequest.getId());
    }

    public static void map2PatientDb(Patient patientDb, Patient patientRequest){
        patientDb.setFirstName(patientRequest.getFirstName());
        patientDb.setLastName(patientRequest.getLastName());
        patientDb.setAge(patientRequest.getAge());
        patientDb.setId(patientRequest.getId());
    }

    public static Appointment toObject(AppointmentDto appointmentDto, Patient patient, Doctor doctor){
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setStartTime(appointmentDto.getAppointmentDate());
        appointment.setCause(appointmentDto.getReason());
        return appointment;
    }


}

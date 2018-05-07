package com.fiipractic.health.control.service;

import com.fiipractic.health.entity.model.Appointment;
import com.fiipractic.health.entity.model.Doctor;
import com.fiipractic.health.entity.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;


@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findAppointmentsByPatient_Id(patientId);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findAppointmentsByDoctor_Id(doctorId);
    }

    @Override
    public Appointment save(Appointment appointment) throws IllegalArgumentException{
        Long doctorId = appointment.getDoctorId(); //id-ul pt doctorul la care se doreste programarea
        List<Appointment> appointments = getAppointmentsByDoctor(doctorId); //toate programarile pt acel doctor;
        Appointment appointment1;
        boolean confirmation = true;

        for(int i=0;i<appointments.size()&&(confirmation==true);i++){ // se parcurg toate programarile de la acel doctor
            appointment1 = appointments.get(i);
            if (appointment1.getStartTime()==appointment.getStartTime()) //daca se gaseste o programare la ora la care dorim noi
                confirmation = false;           //nu mai poate fi setata o noua programare;
        }

        if(confirmation==true) //daca doctorul este disponibil la ora respectiva
            return appointmentRepository.save(appointment); //adaugam programarea
        else
        {
            throw new IllegalArgumentException(String.format("The doctor %s is not available at this hour!",appointment.getDoctor().getFirstName()));
        }
    }

    @Override
    public void delete(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }


}

package com.fiipractic.health.boundry.controller;

import com.fiipractic.health.boundry.exceptions.BadRequestException;
import com.fiipractic.health.boundry.exceptions.NotFoundException;
import com.fiipractic.health.boundry.mapper.ObjectMapper;
import com.fiipractic.health.control.service.DoctorService;
import com.fiipractic.health.control.service.PatientService;
import com.fiipractic.health.entity.model.Doctor;
import com.fiipractic.health.entity.model.Email;
import com.fiipractic.health.entity.model.Patient;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.util.List;

@RestController
@RequestMapping(value = "patients")
public class PatientController {

    private JavaMailSender javaMailSender;
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService, JavaMailSender javaMailSender) {
        this.patientService = patientService;
        this.javaMailSender = javaMailSender;
    }

    @GetMapping
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }


    @GetMapping(value = "/{id}")
    public Patient getPatient(@PathVariable("id") Long id) throws NotFoundException {
       Patient patient = patientService.getPatient(id);
        if (patient == null) {
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        return patient;
    }


    /*
    @GetMapping
    @RequestMapping("/filter")
    public List<Patient> getPatientsByAge(@RequestParam("patientAge") Long patientAge){
        return patientService.findAllPatientWithAge(patientAge);
    }
*/
    @PutMapping(value = "/{id}")
    public Patient updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient) throws BadRequestException, NotFoundException {
        //validate request
        if (!id.equals(patient.getId())) {
            throw new BadRequestException("The id is not the same with id from object");
        }
       Patient patientDb = patientService.getPatient(id);
        if (patientDb == null) {
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        ObjectMapper.map2PatientDb(patientDb, patient);
        return patientService.updatePatient(patientDb);
    }

    private void sendEmail(Patient patient) throws Exception{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        Email email = patient.getEmail();
        try{
            mimeMessageHelper.setTo(email.getEmail()); //doctor are o entitate email; din email avem nevoie de email
            mimeMessageHelper.setSubject("Account created!");
            mimeMessageHelper.setText(String.format("Hello Mr. %s. Your account has been creat", patient.getFirstName()));
            javaMailSender.send(mimeMessage);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}

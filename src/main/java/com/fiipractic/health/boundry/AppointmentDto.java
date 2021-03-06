package com.fiipractic.health.boundry;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class AppointmentDto {

    @NotNull
    private Long doctorId;

    @NotNull
    private Long patientId;
    private Date appointmentDate;
    private String cause;

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

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getReason() {
        return cause;
    }

    public void setReason(String reason) {
        this.cause = reason;
    }
}

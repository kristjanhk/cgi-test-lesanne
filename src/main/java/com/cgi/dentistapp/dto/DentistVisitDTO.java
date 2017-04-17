package com.cgi.dentistapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;

public class DentistVisitDTO {

    @Size(min = 1, max = 50)
    private String dentistName;

    @Size(min = 1, max = 50)
    private String familyPhysicianName;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Calendar visitDate;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private Calendar visitTime;

    public DentistVisitDTO() {
    }

    public DentistVisitDTO(String dentistName, String familyPhysicianName, Calendar visitDate, Calendar visitTime) {
        this.dentistName = dentistName;
        this.familyPhysicianName = familyPhysicianName;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public String getFamilyPhysicianName() {
        return familyPhysicianName;
    }

    public void setFamilyPhysicianName(String familyPhysicianName) {
        this.familyPhysicianName = familyPhysicianName;
    }

    public Calendar getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Calendar visitDate) {
        this.visitDate = visitDate;
    }

    public Calendar getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Calendar visitTime) {
        this.visitTime = visitTime;
    }
}

package com.cgi.dentistapp.dao.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dentist_visit")
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dentist_name")
    private String dentistName;

    @Column(name = "family_physician_name")
    private String familyPhysicianName;

    @Column(name = "visit_datetime")
    private Calendar visitDateTime;

    public DentistVisitEntity() {
    }

    public DentistVisitEntity(String dentistName, String familyPhysicianName, Calendar visitDateTime) {
        this.dentistName = dentistName;
        this.familyPhysicianName = familyPhysicianName;
        this.visitDateTime = visitDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getVisitDateTime() {
        return visitDateTime.getTime().getTime();
    }

    public void setVisitDateTime(Calendar visitDateTime) {
        this.visitDateTime = visitDateTime;
    }

    @Override
    public String toString() {
        return "DentistVisitEntity{" +
                "id=" + id +
                ", dentistName='" + dentistName + '\'' +
                ", familyPhysicianName='" + familyPhysicianName + '\'' +
                ", visitDateTime=" + visitDateTime +
                '}';
    }
}

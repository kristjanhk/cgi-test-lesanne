package com.cgi.dentistapp.dao.entity;

import java.util.List;

/**
 * @author Kristjan Hendrik Küngas
 */
public class DentistVisitQuery {
    private List<DentistVisitEntity> visits;

    public DentistVisitQuery(List<DentistVisitEntity> visits) {
        this.visits = visits;
    }

    public List<DentistVisitEntity> getVisits() {
        return visits;
    }

    public void setVisits(List<DentistVisitEntity> visits) {
        this.visits = visits;
    }
}

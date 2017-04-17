package com.cgi.dentistapp.service;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.dentistapp.dao.DentistVisitRepository;
import com.cgi.dentistapp.dao.entity.DentistVisitEntity;

import static java.util.Calendar.*;

@Service
@Transactional
public class DentistVisitService {
    private final DentistVisitRepository dentistVisitRepository;

    @Autowired
    public DentistVisitService(DentistVisitRepository dentistVisitRepository) {
        this.dentistVisitRepository = dentistVisitRepository;

        //andmed testimiseks
        Calendar date1 = Calendar.getInstance();
        date1.set(2017, Calendar.FEBRUARY, 24);
        Calendar date2 = Calendar.getInstance();
        date2.set(2017, Calendar.APRIL, 1);
        Calendar date3 = Calendar.getInstance();
        date3.set(2017, Calendar.DECEMBER, 24);
        Calendar time = Calendar.getInstance();
        time.set(HOUR, 0);
        time.set(MINUTE, 0);
        addVisit("Jaana Lind", "Vist Siit", date1, time);
        addVisit("Einar Kootikum", "Janar Kootikum", date2, time);
        addVisit("Jaana Lind", "Janar Kootikum", date3, time);
    }

    public void addVisit(String dentistName, String familyPhysicianName, Calendar visitDate, Calendar visitTime) {
        visitDate.set(HOUR, visitTime.get(HOUR));
        visitDate.set(MINUTE, visitTime.get(MINUTE));
        dentistVisitRepository.save(new DentistVisitEntity(dentistName, familyPhysicianName, visitDate));
    }

    public void deleteVisit(long id) {
        dentistVisitRepository.delete(id);
    }

    public List<DentistVisitEntity> listVisits() {
        return dentistVisitRepository.getAllVisits();
    }
}

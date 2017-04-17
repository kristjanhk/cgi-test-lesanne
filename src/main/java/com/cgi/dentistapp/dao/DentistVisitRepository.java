package com.cgi.dentistapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cgi.dentistapp.dao.entity.DentistVisitEntity;

import java.util.List;

@Repository
public interface DentistVisitRepository extends JpaRepository<DentistVisitEntity, Long> {

    @Query(value = "SELECT * FROM dentist_visit", nativeQuery = true)
    List<DentistVisitEntity> getAllVisits();
}

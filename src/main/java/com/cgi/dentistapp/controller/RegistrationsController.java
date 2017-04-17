package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dao.entity.DentistVisitQuery;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.util.Locale.ENGLISH;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author Kristjan Hendrik Küngas
 */
@Controller
@RequestMapping("/registrations")
public class RegistrationsController {
    private final DentistVisitService dentistVisitService;

    @Autowired
    public RegistrationsController(DentistVisitService dentistVisitService) {
        this.dentistVisitService = dentistVisitService;
    }

    @GetMapping
    public String showRegistrations(Model model) {
        model.addAttribute("visits", dentistVisitService.listVisits());
        return "registrations";
    }

    //otsime ja tagastame json objektina ainult need visiidid, kus hambaarsti nimes leidus antud sõne
    @GetMapping(value = "/query/{queryString}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getRegistrations(@PathVariable String queryString) {
        return ok(new DentistVisitQuery(dentistVisitService.listVisits().stream()
                .filter(v -> v.getDentistName().toLowerCase(ENGLISH).contains(queryString.toLowerCase(ENGLISH)))
                .collect(toList())));
    }

    //kustutame antud id-ga visiidi
    @PostMapping(value = "/delete/{deleteString}")
    @ResponseBody
    public ResponseEntity<?> postDeleteRegistration(@PathVariable String deleteString) {
        dentistVisitService.deleteVisit(Long.parseLong(deleteString));
        return ResponseEntity.ok().build();
    }
}

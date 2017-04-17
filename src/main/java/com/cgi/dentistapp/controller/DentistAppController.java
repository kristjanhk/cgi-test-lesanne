package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class DentistAppController {
    private final DentistVisitService dentistVisitService;

    @Autowired
    public DentistAppController(DentistVisitService dentistVisitService) {
        this.dentistVisitService = dentistVisitService;
    }

    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("dentistVisitDTO", new DentistVisitDTO());
        return "form";
    }

    @PostMapping
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getModel().forEach((key, value) -> System.out.println(key + ": " + value));
            return "form";
        }
        dentistVisitService.addVisit(dentistVisitDTO.getDentistName(), dentistVisitDTO.getFamilyPhysicianName(),
                dentistVisitDTO.getVisitDate(), dentistVisitDTO.getVisitTime());
        return "redirect:/registered";
    }

    @GetMapping("/registered")
    public String showRegistrationSuccess() {
        return "registered";
    }
}

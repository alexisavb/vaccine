package org.vaccine.world.application.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vaccine.world.application.dto.request.VaccineRegistrationRequest;
import org.vaccine.world.application.service.VaccineService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(path="/vaccine")
public class VaccineController {

    @Autowired
    VaccineService vaccineService;

    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/modify")
    public String modify() {
        return "modify";
    }

    @GetMapping(value = "/chart")
    public String chart(Model model) {
        model.addAttribute("vaccines",vaccineService.getVaccineTemp());
        return "chart";
    }

    @PostMapping(value = "/")
    public String save(VaccineRegistrationRequest vaccineRequest) {
        log.info("[VaccineController:save] started");
        log.info("Request: {}",vaccineRequest.toString());
        vaccineService.save(vaccineRequest);
        return "home";
    }
}

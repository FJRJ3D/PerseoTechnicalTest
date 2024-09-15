package com.example.PerseoTechnicalTest.controller;

import com.example.PerseoTechnicalTest.model.Experience;
import com.example.PerseoTechnicalTest.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping (path = "api/experience")
@CrossOrigin ("*")
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    @PostMapping(path = "/post")
    public Experience createExperience (@RequestBody Experience experience){
        return experienceService.createExperience(experience);
    }

    @GetMapping(path = "/get")
    public ArrayList<Experience> getAllExperience(){
        return experienceService.getAllExperiences();
    }

    @GetMapping(path = "/get/{id}")
    public Optional<Experience> getExperienceById(@PathVariable Long id){
        return experienceService.getExperienceById(id);
    }

    @PutMapping(path = "/put/{id}")
    public Experience updateExperience(@RequestBody Experience experience, @PathVariable Long id){
        return experienceService.updateExperience(experience, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteExperience(@PathVariable Long id){
        experienceService.deleteExperience(id);
    }
}

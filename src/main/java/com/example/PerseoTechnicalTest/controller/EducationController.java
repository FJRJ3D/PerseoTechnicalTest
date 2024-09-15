package com.example.PerseoTechnicalTest.controller;

import com.example.PerseoTechnicalTest.model.Education;
import com.example.PerseoTechnicalTest.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping (path = "api/education")
@CrossOrigin ("*")
public class EducationController {

    @Autowired
    EducationService educationService;

    @PostMapping (path = "/post")
    public Education createEducation(@RequestBody Education education){
        return educationService.createEducation(education);
    }

    @GetMapping (path = "/get")
    public ArrayList<Education> getAllEducations(){
        return educationService.getAllEducations();
    }

    @GetMapping (path = "/get/{id}")
    public Optional<Education> getEducationById (@PathVariable Long id){
        return educationService.getEducationById(id);
    }

    @PutMapping(path = "/put/{id}")
    public Education updateEducation (@RequestBody Education education, @PathVariable Long id){
        return educationService.updateEducation(education, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteEducation (@PathVariable Long id){
        educationService.deleteEducation(id);
    }
}

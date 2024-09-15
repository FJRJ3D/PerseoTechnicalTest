package com.example.PerseoTechnicalTest.service;

import com.example.PerseoTechnicalTest.model.Education;
import com.example.PerseoTechnicalTest.repository.IEducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EducationService {

    @Autowired
    IEducationRepository iEducationRepository;

    public Education createEducation(Education education){
        return iEducationRepository.save(education);
    }

    public ArrayList<Education> getAllEducations(){
        return (ArrayList<Education>) iEducationRepository.findAll();
    }

    public Optional<Education> getEducationById(Long id){
        return iEducationRepository.findById(id);
    }

    public Education updateEducation(Education education, Long id){
        education.setId(id);
        return iEducationRepository.save(education);
    }

    public void deleteEducation (Long id){
        iEducationRepository.deleteById(id);
    }
}

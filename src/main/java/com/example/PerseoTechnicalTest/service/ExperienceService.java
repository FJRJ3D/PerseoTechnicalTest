package com.example.PerseoTechnicalTest.service;

import com.example.PerseoTechnicalTest.model.Experience;
import com.example.PerseoTechnicalTest.repository.IExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ExperienceService {

    @Autowired
    IExperienceRepository iExperienceRepository;

    public Experience createExperience(Experience experience){
        return iExperienceRepository.save(experience);
    }

    public ArrayList<Experience> getAllExperiences(){
        return (ArrayList<Experience>) iExperienceRepository.findAll();
    }

    public Optional<Experience> getExperienceById(Long id){
        return iExperienceRepository.findById(id);
    }

    public Experience updateExperience (Experience experience, Long id){
        experience.setId(id);
        return iExperienceRepository.save(experience);
    }

    public void deleteExperience (Long id){
        iExperienceRepository.deleteById(id);
    }
}

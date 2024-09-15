package com.example.PerseoTechnicalTest.repository;

import com.example.PerseoTechnicalTest.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExperienceRepository extends JpaRepository<Experience, Long> {
}

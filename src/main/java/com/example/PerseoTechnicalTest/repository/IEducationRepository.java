package com.example.PerseoTechnicalTest.repository;

import com.example.PerseoTechnicalTest.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEducationRepository extends JpaRepository<Education, Long> {
}

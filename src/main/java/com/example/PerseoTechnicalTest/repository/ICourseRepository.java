package com.example.PerseoTechnicalTest.repository;

import com.example.PerseoTechnicalTest.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, Long> {
}

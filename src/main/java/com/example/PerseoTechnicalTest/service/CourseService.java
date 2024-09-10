package com.example.PerseoTechnicalTest.service;

import com.example.PerseoTechnicalTest.model.Course;
import com.example.PerseoTechnicalTest.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    ICourseRepository iCourseRepository;

    public Course createCourse(Course course){
        return iCourseRepository.save(course);
    }

    public Course updateCourse(Course course, Long id){
        course.setId(id);
        return iCourseRepository.save(course);
    }

    public ArrayList<Course> getAllCourses(){
        return (ArrayList<Course>) iCourseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id){
        return iCourseRepository.findById(id);
    }

    public void deleteCourse(Long id){
        iCourseRepository.deleteById(id);
    }
}

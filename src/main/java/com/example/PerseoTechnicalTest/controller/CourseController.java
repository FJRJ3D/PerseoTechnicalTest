package com.example.PerseoTechnicalTest.controller;

import com.example.PerseoTechnicalTest.model.Course;
import com.example.PerseoTechnicalTest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping (path = "api/course")
@CrossOrigin("*")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping(path = "/post")
    public Course createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    @PutMapping(path = "/put/{id}")
    public Course updateCourse(@RequestBody Course course, @PathVariable Long id){
        return courseService.updateCourse(course, id);
    }

    @GetMapping(path = "/get")
    public ArrayList<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping(path = "/get/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
    }
}

package com.example.PerseoTechnicalTest.service;

import com.example.PerseoTechnicalTest.model.Course;
import com.example.PerseoTechnicalTest.repository.ICourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CourseServiceTest {
    @Mock private ICourseRepository iCourseRepository;
    @InjectMocks private  CourseService courseService;

    private Course courseJava;
    private Course courseDocker;

    private List<Course> courseList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        courseJava = new Course();
        courseJava.setId(1);
        courseJava.setCourseName("Java Course");
        courseJava.setCourseDescription("Course about Java and POO");
        courseJava.setStatus(true);

        courseDocker = new Course();
        courseDocker.setId(2);
        courseDocker.setCourseName("Docker Course");
        courseDocker.setCourseDescription("Course about how apply Docker");
        courseDocker.setStatus(false);

        courseList.add(courseJava);
        courseList.add(courseDocker);
    }
    @Test
    void test_create_course() {
        when(iCourseRepository.save(any(Course.class))).thenReturn(courseJava);

        Course result = courseService.createCourse(courseJava);

        assertEquals(1, result.getId());
        assertEquals("Java Course", result.getCourseName());
        assertEquals("Course about Java and POO", result.getCourseDescription());
        assertTrue(result.isStatus());
    }

    @Test
    void test_update_course() {
    }

    @Test
    void test_get_all_courses() {
    }

    @Test
    void test_get_course_by_id() {
    }

    @Test
    void test_delete_course() {
    }
}
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        when(iCourseRepository.save(any(Course.class))).thenReturn(courseJava);

        courseJava.setCourseName("Kubernetes Course");
        courseJava.setCourseDescription("Course about Kubernetes");
        Course result = courseService.updateCourse(courseJava, courseJava.getId());

        assertEquals(1, result.getId());
        assertEquals("Kubernetes Course", result.getCourseName());
        assertEquals("Course about Kubernetes", result.getCourseDescription());
        assertTrue(result.isStatus());
    }

    @Test
    void test_get_all_courses() {
        when(iCourseRepository.findAll()).thenReturn(courseList);

        List <Course> result = courseService.getAllCourses();

        assertEquals(2, result.size());
        assertEquals("Java Course", result.get(0).getCourseName());
        assertEquals("Course about Java and POO", result.get(0).getCourseDescription());
        assertTrue(result.get(0).isStatus());
    }

    @Test
    void test_get_course_by_id() {
        when(iCourseRepository.findById(any(Long.class))).thenReturn(Optional.of(courseDocker));

        Optional<Course> result = courseService.getCourseById(1L);

        assertEquals(2, result.get().getId());
        assertEquals("Docker Course", result.get().getCourseName());
        assertEquals("Course about how apply Docker", result.get().getCourseDescription());
        assertFalse(result.get().isStatus());
    }

    @Test
    void test_delete_course() {
        doNothing().when(iCourseRepository).deleteById(any(Long.class));

        courseService.deleteCourse(1L);

        verify(iCourseRepository, times(1)).deleteById(1L);
    }
}
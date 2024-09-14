package com.example.PerseoTechnicalTest.controller;

import com.example.PerseoTechnicalTest.model.Course;
import com.example.PerseoTechnicalTest.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CourseControllerTest {
    @Mock
    private CourseService courseService;
    @InjectMocks
    private CourseController courseController;

    private MockMvc mockController;

    private Course courseJava;
    private Course courseDocker;

    private List<Course> courseList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        mockController = MockMvcBuilders.standaloneSetup(courseController).build();

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
    void test_create_course()throws Exception {
        when(courseService.createCourse(any(Course.class))).thenReturn(courseJava);

        String courseJava =
                "{\n" +
                        "        \"id\": 1,\n" +
                        "        \"courseName\": \"Java Course\",\n" +
                        "        \"courseDescription\": \"Course about Java and POO\",\n" +
                        "        \"status\": true\n" +
                        "    }";

        mockController
                .perform(post("/api/course/post").contentType(MediaType.APPLICATION_JSON).content(courseJava))
                .andExpect(status().isOk())
                .andExpect(
                        content()
                                .json("{\n" +
                                        "        \"id\": 1,\n" +
                                        "        \"courseName\": \"Java Course\",\n" +
                                        "        \"courseDescription\": \"Course about Java and POO\",\n" +
                                        "        \"status\": true\n" +
                                        "    }"));
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
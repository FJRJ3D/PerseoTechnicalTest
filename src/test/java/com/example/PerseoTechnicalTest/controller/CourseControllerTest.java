package com.example.PerseoTechnicalTest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.PerseoTechnicalTest.model.Course;
import com.example.PerseoTechnicalTest.service.CourseService;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private MockMvc mockController;

    private Course courseJava;
    private Course courseDocker;
    private ArrayList<Course> coursesList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockController = MockMvcBuilders.standaloneSetup(courseController).build();

        courseJava = new Course();
        courseJava.setId(1L);
        courseJava.setCourseName("Java Course");
        courseJava.setCourseDescription("Course about Java and POO");
        courseJava.setStatus(true);

        courseDocker = new Course();
        courseDocker.setId(2L);
        courseDocker.setCourseName("Docker Course");
        courseDocker.setCourseDescription("Course about how to apply Docker");
        courseDocker.setStatus(false);

        coursesList.add(courseJava);
        coursesList.add(courseDocker);
    }

    @Test
    void createCourse() throws Exception {
        when(courseService.createCourse(any(Course.class))).thenReturn(courseJava);

        String courseJson =
                "{\"id\": 1,\n"
                        + "\"courseName\": \"Java Course\",\n"
                        + "\"courseDescription\": \"Course about Java and POO\",\n"
                        + "\"status\": true\n"
                        + "}";

        mockController
                .perform(post("/api/course/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseJson))
                .andExpect(status().isOk())
                .andExpect(content().json(courseJson));
    }

    @Test
    void getAllCourses() throws Exception {
        when(courseService.getAllCourses()).thenReturn(coursesList);

        mockController
                .perform(get("/api/course/get"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n"
                        + "  {\n"
                        + "    \"id\": 1,\n"
                        + "    \"courseName\": \"Java Course\",\n"
                        + "    \"courseDescription\": \"Course about Java and POO\",\n"
                        + "    \"status\": true\n"
                        + "  },\n"
                        + "  {\n"
                        + "    \"id\": 2,\n"
                        + "    \"courseName\": \"Docker Course\",\n"
                        + "    \"courseDescription\": \"Course about how to apply Docker\",\n"
                        + "    \"status\": false\n"
                        + "  }\n"
                        + "]"));
    }

    @Test
    void getCourseById() throws Exception {
        when(courseService.getCourseById(anyLong())).thenReturn(Optional.of(courseJava));

        mockController
                .perform(get("/api/course/get/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n"
                        + "  \"id\": 1,\n"
                        + "  \"courseName\": \"Java Course\",\n"
                        + "  \"courseDescription\": \"Course about Java and POO\",\n"
                        + "  \"status\": true\n"
                        + "}"));
    }

    @Test
    void updateCourse() throws Exception {
        Course updatedCourse = new Course();
        updatedCourse.setId(2L);
        updatedCourse.setCourseDescription("Updated Docker course description");

        String updateCourseJson =
                "{\"id\": 2,\n"
                        + "\"courseDescription\": \"Updated Docker course description\"\n"
                        + "}";

        mockController
                .perform(put("/api/course/put/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateCourseJson))
                .andExpect(status().isOk());

        verify(courseService).updateCourse(any(Course.class), anyLong());
    }

    @Test
    void deleteCourse() throws Exception {
        doNothing().when(courseService).deleteCourse(1L);

        mockController
                .perform(delete("/api/course/delete/1"))
                .andExpect(status().isOk());

        verify(courseService).deleteCourse(1L);
    }
}

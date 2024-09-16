package com.example.PerseoTechnicalTest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.PerseoTechnicalTest.model.Education;
import com.example.PerseoTechnicalTest.service.EducationService;
import java.time.LocalDate;
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

class EducationControllerTest {

    @Mock
    private EducationService educationService;

    @InjectMocks
    private EducationController educationController;

    private MockMvc mockMvc;

    private Education educationBachelors;
    private Education educationMasters;
    private ArrayList<Education> educationList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(educationController).build();

        educationBachelors = new Education();
        educationBachelors.setId(1L);
        educationBachelors.setTitle("Bachelor's Degree");
        educationBachelors.setSchool("Tech University");
        educationBachelors.setStartDate(LocalDate.of(2015, 9, 1));
        educationBachelors.setEndDate(LocalDate.of(2019, 6, 30));
        educationBachelors.setDescription("Completed a bachelor's degree in Computer Science");

        educationMasters = new Education();
        educationMasters.setId(2L);
        educationMasters.setTitle("Master's Degree");
        educationMasters.setSchool("Engineering School");
        educationMasters.setStartDate(LocalDate.of(2020, 9, 1));
        educationMasters.setEndDate(LocalDate.of(2022, 6, 30));
        educationMasters.setDescription("Completed a master's degree in Software Engineering");

        educationList.add(educationBachelors);
        educationList.add(educationMasters);
    }

    @Test
    void createEducation() throws Exception {
        when(educationService.createEducation(any(Education.class))).thenReturn(educationBachelors);

        String educationJson =
                "{\"id\": 1,\n"
                        + "\"title\": \"Bachelor's Degree\",\n"
                        + "\"school\": \"Tech University\",\n"
                        + "\"startDate\": \"01-09-2015\",\n"
                        + "\"endDate\": \"30-06-2019\",\n"
                        + "\"description\": \"Completed a bachelor's degree in Computer Science\"\n"
                        + "}";

        mockMvc
                .perform(post("/api/education/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(educationJson))
                .andExpect(status().isOk())
                .andExpect(content().json(educationJson));
    }

    @Test
    void getAllEducations() throws Exception {
        when(educationService.getAllEducations()).thenReturn(educationList);

        mockMvc
                .perform(get("/api/education/get"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n"
                        + "  {\n"
                        + "    \"id\": 1,\n"
                        + "    \"title\": \"Bachelor's Degree\",\n"
                        + "    \"school\": \"Tech University\",\n"
                        + "    \"startDate\": \"01-09-2015\",\n"
                        + "    \"endDate\": \"30-06-2019\",\n"
                        + "    \"description\": \"Completed a bachelor's degree in Computer Science\"\n"
                        + "  },\n"
                        + "  {\n"
                        + "    \"id\": 2,\n"
                        + "    \"title\": \"Master's Degree\",\n"
                        + "    \"school\": \"Engineering School\",\n"
                        + "    \"startDate\": \"01-09-2020\",\n"
                        + "    \"endDate\": \"30-06-2022\",\n"
                        + "    \"description\": \"Completed a master's degree in Software Engineering\"\n"
                        + "  }\n"
                        + "]"));
    }

    @Test
    void getEducationById() throws Exception {
        when(educationService.getEducationById(anyLong())).thenReturn(Optional.of(educationBachelors));

        mockMvc
                .perform(get("/api/education/get/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n"
                        + "  \"id\": 1,\n"
                        + "  \"title\": \"Bachelor's Degree\",\n"
                        + "  \"school\": \"Tech University\",\n"
                        + "  \"startDate\": \"01-09-2015\",\n"
                        + "  \"endDate\": \"30-06-2019\",\n"
                        + "  \"description\": \"Completed a bachelor's degree in Computer Science\"\n"
                        + "}"));
    }

    @Test
    void updateEducation() throws Exception {
        Education updatedEducation = new Education();
        updatedEducation.setId(2L);
        updatedEducation.setDescription("Updated description");

        String updateEducationJson =
                "{\"id\": 2,\n"
                        + "\"description\": \"Updated description\"\n"
                        + "}";

        mockMvc
                .perform(put("/api/education/put/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateEducationJson))
                .andExpect(status().isOk());

        verify(educationService).updateEducation(any(Education.class), anyLong());
    }

    @Test
    void deleteEducation() throws Exception {
        doNothing().when(educationService).deleteEducation(1L);

        mockMvc
                .perform(delete("/api/education/delete/1"))
                .andExpect(status().isOk());

        verify(educationService).deleteEducation(1L);
    }
}

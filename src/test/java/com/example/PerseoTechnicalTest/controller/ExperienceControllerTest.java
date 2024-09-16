package com.example.PerseoTechnicalTest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.PerseoTechnicalTest.model.Experience;
import com.example.PerseoTechnicalTest.service.ExperienceService;
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

class ExperienceControllerTest {

    @Mock
    private ExperienceService experienceService;

    @InjectMocks
    private ExperienceController experienceController;

    private MockMvc mockMvc;

    private Experience experienceJava;
    private Experience experienceDocker;
    private ArrayList<Experience> experiencesList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(experienceController).build();

        experienceJava = new Experience();
        experienceJava.setId(1L);
        experienceJava.setCompanyName("Java Inc.");
        experienceJava.setPosition("Java Developer");
        experienceJava.setDescription("Developed Java applications");

        experienceDocker = new Experience();
        experienceDocker.setId(2L);
        experienceDocker.setCompanyName("Docker Ltd.");
        experienceDocker.setPosition("Docker Specialist");
        experienceDocker.setDescription("Worked on Docker deployments");

        experiencesList.add(experienceJava);
        experiencesList.add(experienceDocker);
    }

    @Test
    void createExperience() throws Exception {
        when(experienceService.createExperience(any(Experience.class))).thenReturn(experienceJava);

        String experienceJson =
                "{\"id\": 1,\n"
                        + "\"companyName\": \"Java Inc.\",\n"
                        + "\"position\": \"Java Developer\",\n"
                        + "\"description\": \"Developed Java applications\"\n"
                        + "}";

        mockMvc
                .perform(post("/api/experience/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(experienceJson))
                .andExpect(status().isOk())
                .andExpect(content().json(experienceJson));
    }

    @Test
    void getAllExperiences() throws Exception {
        when(experienceService.getAllExperiences()).thenReturn(experiencesList);

        mockMvc
                .perform(get("/api/experience/get"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n"
                        + "  {\n"
                        + "    \"id\": 1,\n"
                        + "    \"companyName\": \"Java Inc.\",\n"
                        + "    \"position\": \"Java Developer\",\n"
                        + "    \"description\": \"Developed Java applications\"\n"
                        + "  },\n"
                        + "  {\n"
                        + "    \"id\": 2,\n"
                        + "    \"companyName\": \"Docker Ltd.\",\n"
                        + "    \"position\": \"Docker Specialist\",\n"
                        + "    \"description\": \"Worked on Docker deployments\"\n"
                        + "  }\n"
                        + "]"));
    }

    @Test
    void getExperienceById() throws Exception {
        when(experienceService.getExperienceById(anyLong())).thenReturn(Optional.of(experienceJava));

        mockMvc
                .perform(get("/api/experience/get/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n"
                        + "  \"id\": 1,\n"
                        + "  \"companyName\": \"Java Inc.\",\n"
                        + "  \"position\": \"Java Developer\",\n"
                        + "  \"description\": \"Developed Java applications\"\n"
                        + "}"));
    }

    @Test
    void updateExperience() throws Exception {
        Experience updatedExperience = new Experience();
        updatedExperience.setId(2L);
        updatedExperience.setDescription("Updated description");

        String updateExperienceJson =
                "{\"id\": 2,\n"
                        + "\"description\": \"Updated description\"\n"
                        + "}";

        mockMvc
                .perform(put("/api/experience/put/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateExperienceJson))
                .andExpect(status().isOk());

        verify(experienceService).updateExperience(any(Experience.class), anyLong());
    }

    @Test
    void deleteExperience() throws Exception {
        doNothing().when(experienceService).deleteExperience(1L);

        mockMvc
                .perform(delete("/api/experience/delete/1"))
                .andExpect(status().isOk());

        verify(experienceService).deleteExperience(1L);
    }
}

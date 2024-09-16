package com.example.PerseoTechnicalTest.service;

import com.example.PerseoTechnicalTest.model.Experience;
import com.example.PerseoTechnicalTest.repository.IExperienceRepository;
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

class ExperienceServiceTest {
    @Mock private IExperienceRepository iExperienceRepository;
    @InjectMocks private ExperienceService experienceService;

    private Experience experience1;
    private Experience experience2;

    private List<Experience> experienceList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        experience1 = new Experience();
        experience1.setId(1L);
        experience1.setPosition("Software Engineer");
        experience1.setCompanyName("Tech Corp");

        experience2 = new Experience();
        experience2.setId(2L);
        experience2.setPosition("DevOps Engineer");
        experience2.setCompanyName("Cloud Inc");

        experienceList.add(experience1);
        experienceList.add(experience2);
    }

    @Test
    void test_create_experience() {
        when(iExperienceRepository.save(any(Experience.class))).thenReturn(experience1);

        Experience result = experienceService.createExperience(experience1);

        assertEquals(1L, result.getId());
        assertEquals("Software Engineer", result.getPosition());
        assertEquals("Tech Corp", result.getCompanyName());
    }

    @Test
    void test_get_all_experiences() {
        when(iExperienceRepository.findAll()).thenReturn(experienceList);

        List<Experience> result = experienceService.getAllExperiences();

        assertEquals(2, result.size());
        assertEquals("Software Engineer", result.get(0).getPosition());
        assertEquals("DevOps Engineer", result.get(1).getPosition());
    }

    @Test
    void test_get_experience_by_id() {
        when(iExperienceRepository.findById(any(Long.class))).thenReturn(Optional.of(experience1));

        Optional<Experience> result = experienceService.getExperienceById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Software Engineer", result.get().getPosition());
    }

    @Test
    void test_update_experience() {
        when(iExperienceRepository.save(any(Experience.class))).thenReturn(experience1);

        experience1.setPosition("Lead Engineer");
        Experience result = experienceService.updateExperience(experience1, 1L);

        assertEquals(1L, result.getId());
        assertEquals("Lead Engineer", result.getPosition());
    }

    @Test
    void test_delete_experience() {
        doNothing().when(iExperienceRepository).deleteById(any(Long.class));

        experienceService.deleteExperience(1L);

        verify(iExperienceRepository, times(1)).deleteById(1L);
    }
}

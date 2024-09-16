package com.example.PerseoTechnicalTest.service;

import com.example.PerseoTechnicalTest.model.Education;
import com.example.PerseoTechnicalTest.repository.IEducationRepository;
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

class EducationServiceTest {
    @Mock private IEducationRepository iEducationRepository;
    @InjectMocks private EducationService educationService;

    private Education education1;
    private Education education2;

    private List<Education> educationList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        education1 = new Education();
        education1.setId(1L);
        education1.setTitle("Computer Science");
        education1.setSchool("MIT");

        education2 = new Education();
        education2.setId(2L);
        education2.setTitle("Software Engineering");
        education2.setSchool("Stanford");

        educationList.add(education1);
        educationList.add(education2);
    }

    @Test
    void test_create_education() {
        when(iEducationRepository.save(any(Education.class))).thenReturn(education1);

        Education result = educationService.createEducation(education1);

        assertEquals(1L, result.getId());
        assertEquals("Computer Science", result.getTitle());
        assertEquals("MIT", result.getSchool());
    }

    @Test
    void test_get_all_educations() {
        when(iEducationRepository.findAll()).thenReturn(educationList);

        List<Education> result = educationService.getAllEducations();

        assertEquals(2, result.size());
        assertEquals("Computer Science", result.get(0).getTitle());
        assertEquals("Software Engineering", result.get(1).getTitle());
    }

    @Test
    void test_get_education_by_id() {
        when(iEducationRepository.findById(any(Long.class))).thenReturn(Optional.of(education1));

        Optional<Education> result = educationService.getEducationById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Computer Science", result.get().getTitle());
    }

    @Test
    void test_update_education() {
        when(iEducationRepository.save(any(Education.class))).thenReturn(education1);

        education1.setTitle("Data Science");
        Education result = educationService.updateEducation(education1, 1L);

        assertEquals(1L, result.getId());
        assertEquals("Data Science", result.getTitle());
    }

    @Test
    void test_delete_education() {
        doNothing().when(iEducationRepository).deleteById(any(Long.class));

        educationService.deleteEducation(1L);

        verify(iEducationRepository, times(1)).deleteById(1L);
    }
}

package com.example.PerseoTechnicalTest.service;

import com.example.PerseoTechnicalTest.model.Course;
import com.example.PerseoTechnicalTest.model.Education;
import com.example.PerseoTechnicalTest.model.Experience;
import com.example.PerseoTechnicalTest.model.User;
import com.example.PerseoTechnicalTest.repository.ICourseRepository;
import com.example.PerseoTechnicalTest.repository.IEducationRepository;
import com.example.PerseoTechnicalTest.repository.IExperienceRepository;
import com.example.PerseoTechnicalTest.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    ICourseRepository iCourseRepository;

    @Autowired
    IExperienceRepository iExperienceRepository;

    @Autowired
    IEducationRepository iEducationRepository;

    public User createUser(User user){
        return iUserRepository.save(user);
    }

    public User updateUser(User user, Long id){
        user.setId(id);
        return iUserRepository.save(user);
    }

    public ArrayList<User> getAllUsers(){
        return (ArrayList<User>) iUserRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return iUserRepository.findById(id);
    }

    public void deleteUser(Long id){
        iUserRepository.deleteById(id);
    }

    public User updateCourseOwner(Long idCourse, Long idUser){
        User user = iUserRepository.findById(idUser).get();
        Course course = iCourseRepository.findById(idCourse).get();

        user.getCourses().add(course);

        return iUserRepository.save(user);
    }

    public User addCourseToLoggedUser(String username, Long idCourse){
        User user = iUserRepository.findByUsername(username).get();
        Course course = iCourseRepository.findById(idCourse).get();

        user.getCourses().add(course);

        return iUserRepository.save(user);
    }

    public User addExperienceToLoggedUser(String username, Experience experience){
        User user = iUserRepository.findByUsername(username).get();
        Experience experienceAdd = iExperienceRepository.save(experience);

        experienceAdd.setUser(user);

        return iUserRepository.save(user);
    }

    public User addEducationToLoggedUser(String username, Education education){
        User user = iUserRepository.findByUsername(username).get();
        Education educationAdd = iEducationRepository.save(education);

        educationAdd.setUser(user);

        return iUserRepository.save(user);
    }
}

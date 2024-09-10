package com.example.PerseoTechnicalTest.service;

import com.example.PerseoTechnicalTest.model.User;
import com.example.PerseoTechnicalTest.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

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
}

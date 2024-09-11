package com.example.PerseoTechnicalTest.controller;

import com.example.PerseoTechnicalTest.model.User;
import com.example.PerseoTechnicalTest.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/post")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping(path = "/put/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id){
        return userService.updateUser(user, id);
    }

    @GetMapping(path = "/get")
    public ArrayList<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(path = "/get/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping(path = "/putOwner/{idUser}/course/{idCourse}")
    public User updateCourseOwner(@PathVariable Long idCourse, @PathVariable Long idUser){
        return userService.updateCourseOwner(idCourse, idUser);
    }

    @PutMapping(path = "/addCourse/{idCourse}")
    public User addCourseToLoggedUser(@PathVariable Long idCourse){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userService.addCourseToLoggedUser(username, idCourse);
    }
}

package com.example.PerseoTechnicalTest.repository;

import com.example.PerseoTechnicalTest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}

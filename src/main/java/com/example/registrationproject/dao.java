package com.example.registrationproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface dao extends JpaRepository<User, Integer> {
    User findByLogin(String login);
    boolean existsByLogin(String login);
}

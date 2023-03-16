package com.example.air_quality_check.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.air_quality_check.model.UserModel.User;

/* JPArepository which manages data in this user details, with respect to ID */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
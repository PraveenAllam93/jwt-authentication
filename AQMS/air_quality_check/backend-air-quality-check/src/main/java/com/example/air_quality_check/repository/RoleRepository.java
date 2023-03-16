package com.example.air_quality_check.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.air_quality_check.model.UserModel.Role;
import com.example.air_quality_check.model.UserModel.RoleName;

/* JPArepository which manages data in this roles, with respect to ID */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
package com.example.security_biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.security_biblioteca.model.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByRoleName(String roleName);
}

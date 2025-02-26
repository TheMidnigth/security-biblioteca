package com.example.security_biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.security_biblioteca.model.LoanModel;
import com.example.security_biblioteca.model.UserModel;

@Repository
public interface LoanRepository extends JpaRepository<LoanModel, Long> {
    Optional<LoanModel> findByUserAndStatus(UserModel user, String status);

}

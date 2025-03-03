package com.example.security_biblioteca.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.security_biblioteca.model.LoanModel;


@Repository
public interface LoanRepository extends JpaRepository<LoanModel, Long> {
    List<LoanModel> findByUser_Id(Long userId);

}

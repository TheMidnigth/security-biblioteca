package com.example.security_biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security_biblioteca.model.LoanModel;
import com.example.security_biblioteca.model.UserModel;
import com.example.security_biblioteca.repository.LoanRepository;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public LoanModel createLoan(LoanModel loan) {
        return loanRepository.save(loan);
    }

    public List<LoanModel> getActiveLoansByUser (UserModel user) {
        return loanRepository.findByUserAndStatus(user, "active")
                            .map(List::of)
                            .orElseGet(List::of);
    }

}

package com.adelsonsljunior.services;

import com.adelsonsljunior.dtos.transaction.TransactionResponseDTO;
import com.adelsonsljunior.dtos.transaction.TransactionRequestDTO;
import com.adelsonsljunior.entities.Transaction;
import com.adelsonsljunior.entities.User;
import com.adelsonsljunior.exceptions.ResourceNotFoundException;
import com.adelsonsljunior.repositories.TransactionRepository;
import com.adelsonsljunior.repositories.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TransactionService {

    @Inject
    TransactionRepository depositRepository;
    @Inject
    UserRepository userRepository;

    @Transactional
    public TransactionResponseDTO deposit(TransactionRequestDTO depositRequestDTO) {

        User user = userRepository.findByIdOptional(depositRequestDTO.userId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with ID " + depositRequestDTO.userId() + " not found"));

        Transaction deposit = new Transaction();
        deposit.setValue(depositRequestDTO.value());
        deposit.setDate(depositRequestDTO.date());
        deposit.setUser(user);

        depositRepository.persist(deposit);

        return deposit.tResponse();

    }
}

package com.adelsonsljunior.services;

import com.adelsonsljunior.dtos.transaction.TransactionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

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
    TransactionRepository transactionRepository;
    @Inject
    UserRepository userRepository;

    @Transactional
    public TransactionResponseDTO deposit(TransactionRequestDTO data) {

        User user = userRepository.findByIdOptional(data.userId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with ID " + data.userId() + " not found"));

        Transaction deposit = new Transaction();
        deposit.setValue(data.value());
        deposit.setDate(data.date());
        deposit.setUser(user);

        transactionRepository.persist(deposit);

        return deposit.tResponse();

    }

    public List<TransactionResponseDTO> findAllByUserId(Long userId) {

        return transactionRepository.findAllByUserId(userId).stream()
                .map(Transaction::tResponse)
                .collect(Collectors.toList());

    }
}

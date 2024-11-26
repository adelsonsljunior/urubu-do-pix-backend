package com.adelsonsljunior.services;

import com.adelsonsljunior.dtos.transaction.TransactionResponseDTO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import com.adelsonsljunior.dtos.transaction.BalanceResponseDTO;
import com.adelsonsljunior.dtos.transaction.DepositRequestDTO;
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
    public TransactionResponseDTO deposit(DepositRequestDTO data) {

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

    public BalanceResponseDTO getBalance(Long transactionId, LocalDate currenDate) {

        Transaction transaction = transactionRepository.findByIdOptional(transactionId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Transaction with ID " + transactionId + " not found"));

        double currentValue = calculeteBalance(transaction, currenDate);

        return new BalanceResponseDTO(
                transaction.getId(),
                transaction.getUser().getId(),
                transaction.getValue(),
                currentValue,
                transaction.getDate());

    }

    private double calculeteBalance(Transaction transaction, LocalDate currenDate) {

        long days = ChronoUnit.DAYS.between(transaction.getDate(), currenDate);

        double currentValue = transaction.getValue() * days * (33.33 / 100);

        return currentValue;
    }
}

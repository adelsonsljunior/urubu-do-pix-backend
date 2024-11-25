package com.adelsonsljunior.repositories;

import java.util.List;

import com.adelsonsljunior.dtos.transaction.TransactionResponseDTO;
import com.adelsonsljunior.entities.Transaction;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<Transaction> {

    // Método para encontrar todas as transações por userId
    public List<Transaction> findAllByUserId(Long userId) {

        // Consulta usando o Panache para buscar transações de um usuário específico
        return find("user.id", userId).list();
    }
}

package com.adelsonsljunior.repositories;

import com.adelsonsljunior.entities.Transaction;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<Transaction> {
    
}

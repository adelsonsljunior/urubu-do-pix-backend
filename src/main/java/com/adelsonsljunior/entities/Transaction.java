package com.adelsonsljunior.entities;

import java.time.LocalDate;

import com.adelsonsljunior.dtos.transaction.TransactionResponseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double value;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Definindo chave estrangeira
    private User user;

    public Transaction(Long id, double value, LocalDate date, User user) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.user = user;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TransactionResponseDTO tResponse() {
        return new TransactionResponseDTO(
                this.id,
                this.user.getId(),
                this.value,
                this.date
        );
    }

}

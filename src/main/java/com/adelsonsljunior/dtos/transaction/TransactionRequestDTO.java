package com.adelsonsljunior.dtos.transaction;

import java.time.LocalDate;

public record TransactionRequestDTO(
        Long userId,
        double value,
        LocalDate date) {
}

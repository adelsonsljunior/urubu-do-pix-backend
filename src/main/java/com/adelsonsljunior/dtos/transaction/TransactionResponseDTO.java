package com.adelsonsljunior.dtos.transaction;

import java.time.LocalDate;

public record TransactionResponseDTO(
    Long id,
    Long userId,
    double value,
    LocalDate date
) {
    
}

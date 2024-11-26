package com.adelsonsljunior.dtos.transaction;

import java.time.LocalDate;

public record DepositRequestDTO(
                Long userId,
                double value,
                LocalDate date) {
}

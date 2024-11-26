package com.adelsonsljunior.dtos.transaction;

import java.time.LocalDate;

public record BalanceResponseDTO(
        Long id,
        Long userId,
        double initalValue,
        double currentValue,
        LocalDate startDate) {

}

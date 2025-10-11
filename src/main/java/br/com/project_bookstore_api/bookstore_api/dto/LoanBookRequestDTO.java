package br.com.project_bookstore_api.bookstore_api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record LoanBookRequestDTO(
        LocalDateTime dateHourLoan,
        LocalDateTime dateHourLoanReturn,
        UUID userId,
        UUID bookCopyId
) {}

package br.com.project_bookstore_api.bookstore_api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record LoanBookResponseDTO(
        UUID id,
        LocalDateTime dateHourLoan,
        LocalDateTime dateHourLoanReturn,
        UserResponseDTO user,
        BookCopyResponseDTO bookCopy
) {}
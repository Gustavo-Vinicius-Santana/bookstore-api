package br.com.project_bookstore_api.bookstore_api.dto;

import java.time.LocalDateTime;

public record ResponseExceptionDto(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message
) {}
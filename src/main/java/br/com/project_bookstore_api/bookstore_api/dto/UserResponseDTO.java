package br.com.project_bookstore_api.bookstore_api.dto;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String cpf
) {}

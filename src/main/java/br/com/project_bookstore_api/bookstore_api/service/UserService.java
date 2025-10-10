package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.dto.UserRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.UserResponseDTO;
import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserResponseDTO> findAll();
    UserResponseDTO findById(UUID id);
    UserResponseDTO create(UserRequestDTO userRequestDTO);
    UserResponseDTO update(UUID id, UserRequestDTO userRequestDTO);
    void delete(UUID id);
}
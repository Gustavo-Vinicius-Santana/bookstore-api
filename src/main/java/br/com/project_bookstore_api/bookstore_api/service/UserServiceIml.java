package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.dto.UserRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.UserResponseDTO;
import br.com.project_bookstore_api.bookstore_api.model.User;
import br.com.project_bookstore_api.bookstore_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceIml implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return user.toDto();
    }

    @Override
    public UserResponseDTO create(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email already exists: " + dto.email());
        }

        if (userRepository.existsByCpf(dto.cpf())) {
            throw new RuntimeException("CPF already exists: " + dto.cpf());
        }

        User user = User.fromDto(dto);
        User savedUser = userRepository.save(user);

        return savedUser.toDto();
    }

    @Override
    public UserResponseDTO update(UUID id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (!user.getEmail().equals(dto.email()) && userRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email already exists: " + dto.email());
        }

        if (!user.getCpf().equals(dto.cpf()) && userRepository.existsByCpf(dto.cpf())) {
            throw new RuntimeException("CPF already exists: " + dto.cpf());
        }

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setCpf(dto.cpf());

        User updatedUser = userRepository.save(user);
        return updatedUser.toDto();
    }

    @Override
    public void delete(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }
}

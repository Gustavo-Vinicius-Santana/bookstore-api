package br.com.project_bookstore_api.bookstore_api.model;

import br.com.project_bookstore_api.bookstore_api.dto.UserRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.UserResponseDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String cpf;

    public static User fromDto(UserRequestDTO dto) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .cpf(dto.cpf())
                .build();
    }

    public UserResponseDTO toDto() {
        return new UserResponseDTO(id, name, email, cpf);
    }
}

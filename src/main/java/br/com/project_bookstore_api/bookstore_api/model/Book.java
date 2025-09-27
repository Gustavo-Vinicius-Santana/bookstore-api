package br.com.project_bookstore_api.bookstore_api.model;

import br.com.project_bookstore_api.bookstore_api.dto.BookRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.BookResponseDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String title;

    private String description;

    private Integer pageCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public static Book fromDto(BookRequestDTO dto) {
        return Book.builder()
                .title(dto.title())
                .description(dto.description())
                .pageCount(dto.pageCount())
                .build();
    }
}

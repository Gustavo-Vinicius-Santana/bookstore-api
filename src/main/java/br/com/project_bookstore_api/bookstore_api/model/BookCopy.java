package br.com.project_bookstore_api.bookstore_api.model;

import br.com.project_bookstore_api.bookstore_api.dto.BookCopyRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "book_copies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCopy {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookCondition conditionStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus bookStatus;

    public static BookCopy fromDto(BookCopyRequestDTO dto) {
        return BookCopy.builder()
                .conditionStatus(dto.conditionStatus())
                .bookStatus(dto.bookStatus())
                .build();
    }
}
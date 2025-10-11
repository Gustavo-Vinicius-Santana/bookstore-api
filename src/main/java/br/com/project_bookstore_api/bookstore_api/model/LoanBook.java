package br.com.project_bookstore_api.bookstore_api.model;

import br.com.project_bookstore_api.bookstore_api.dto.LoanBookRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.LoanBookResponseDTO;
import br.com.project_bookstore_api.bookstore_api.dto.BookCopyResponseDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loans_books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanBook {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime dateHourLoan;

    @Column(nullable = false)
    private LocalDateTime dateHourLoanReturn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_copy_id", nullable = false)
    private BookCopy bookCopy;

    public static LoanBook fromDto(LoanBookRequestDTO dto, User user, BookCopy bookCopy) {
        return LoanBook.builder()
                .dateHourLoan(dto.dateHourLoan())
                .dateHourLoanReturn(dto.dateHourLoanReturn())
                .user(user)
                .bookCopy(bookCopy)
                .build();
    }

    public LoanBookResponseDTO toDto() {
        return new LoanBookResponseDTO(
                id,
                dateHourLoan,
                dateHourLoanReturn,
                user.toDto(),
                new BookCopyResponseDTO(bookCopy)
        );
    }
}
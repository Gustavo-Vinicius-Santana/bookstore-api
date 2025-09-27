package br.com.project_bookstore_api.bookstore_api.dto;

import br.com.project_bookstore_api.bookstore_api.model.Book;
import java.util.UUID;

public record BookResponseDTO(
        UUID id,
        String title,
        String description,
        Integer pageCount,
        UUID authorId,
        String authorName
) {
    public BookResponseDTO(Book book) {
        this(
                book.getId(),
                book.getTitle(),
                book.getDescription(),
                book.getPageCount(),
                book.getAuthor().getId(),
                book.getAuthor().getName()
        );
    }
}
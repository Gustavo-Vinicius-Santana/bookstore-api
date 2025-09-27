package br.com.project_bookstore_api.bookstore_api.dto;

import br.com.project_bookstore_api.bookstore_api.model.Book;

import java.util.UUID;

public record BookRequestDTO(
        String title,
        String description,
        Integer pageCount,
        UUID authorId
) {
    public BookRequestDTO(Book book) {
        this(
                book.getTitle(),
                book.getDescription(),
                book.getPageCount(),
                book.getAuthor() != null ? book.getAuthor().getId() : null
        );
    }
}
package br.com.project_bookstore_api.bookstore_api.dto;

import br.com.project_bookstore_api.bookstore_api.model.BookCopy;
import br.com.project_bookstore_api.bookstore_api.model.BookCondition;
import br.com.project_bookstore_api.bookstore_api.model.BookStatus;

import java.util.UUID;

public record BookCopyResponseDTO(
        UUID id,
        UUID bookId,
        String bookTitle,
        BookCondition conditionStatus,
        BookStatus bookStatus
) {
    public BookCopyResponseDTO(BookCopy bookCopy) {
        this(
                bookCopy.getId(),
                bookCopy.getBook().getId(),
                bookCopy.getBook().getTitle(),
                bookCopy.getConditionStatus(),
                bookCopy.getBookStatus()
        );
    }
}
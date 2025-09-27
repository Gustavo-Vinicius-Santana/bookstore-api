package br.com.project_bookstore_api.bookstore_api.dto;

import br.com.project_bookstore_api.bookstore_api.model.BookCopy;
import br.com.project_bookstore_api.bookstore_api.model.BookCondition;
import br.com.project_bookstore_api.bookstore_api.model.BookStatus;

import java.util.UUID;

public record BookCopyRequestDTO(
        UUID bookId,
        BookCondition conditionStatus,
        BookStatus bookStatus
) {
    public BookCopyRequestDTO(BookCopy bookCopy) {
        this(
                bookCopy.getBook() != null ? bookCopy.getBook().getId() : null,
                bookCopy.getConditionStatus(),
                bookCopy.getBookStatus()
        );
    }
}
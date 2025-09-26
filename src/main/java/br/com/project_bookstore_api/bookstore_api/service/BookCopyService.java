package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.model.BookCopy;
import br.com.project_bookstore_api.bookstore_api.model.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookCopyService {
    Page<BookCopy> findAll(Pageable pagination);
    BookCopy findById(UUID id);
    long countByBookId(UUID bookId);
    long countByBookIdAndStatus(UUID bookId, BookStatus status);
    BookCopy save(BookCopy bookCopy);
    BookCopy update(UUID id, BookCopy bookCopy);
    void deleteId(UUID id);
}
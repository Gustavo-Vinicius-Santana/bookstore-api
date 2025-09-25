package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.model.BookCopy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookCopyService {
    Page<BookCopy> findAll(Pageable pagination);
    BookCopy findById(UUID id);
    BookCopy findByName(String name);
    BookCopy save(BookCopy bookCopy);
    BookCopy update(UUID id, BookCopy bookCopy);
    void deleteId(UUID id);
}

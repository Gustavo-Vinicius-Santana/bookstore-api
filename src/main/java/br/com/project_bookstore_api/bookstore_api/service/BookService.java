package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookService {
    Page<Book> findAll(Pageable pagination);
    Book findById(UUID id);
    Book findByName(String name);
    Book save(Book book);
    Book update(UUID id, Book book);
    void deleteId(UUID id);
}

package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AuthorService {
    Page<Author> findAll(Pageable pagination);
    Author findById(UUID id);
    Author findByName(String name);
    Author save(Author author);
    Author update(UUID id, Author author);
    void deleteId(UUID id);
}

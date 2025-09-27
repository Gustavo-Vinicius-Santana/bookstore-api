package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.dto.BookRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.BookResponseDTO;
import br.com.project_bookstore_api.bookstore_api.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookService {
    Page<BookResponseDTO> findAll(Pageable pagination);
    BookResponseDTO findById(UUID id);
    Page<BookResponseDTO> findByTitle(String title, Pageable pagination);
    BookResponseDTO save(BookRequestDTO book);
    BookRequestDTO update(UUID id, BookRequestDTO book);
    void deleteId(UUID id);
}
package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.dto.BookCopyRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.BookCopyResponseDTO;
import br.com.project_bookstore_api.bookstore_api.model.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookCopyService {
    Page<BookCopyResponseDTO> findAll(Pageable pagination);
    BookCopyResponseDTO findById(UUID id);
    long countByBookId(UUID bookId);
    long countByBookIdAndStatus(UUID bookId, BookStatus status);
    BookCopyResponseDTO save(BookCopyRequestDTO bookCopy);
    BookCopyRequestDTO update(UUID id, BookCopyRequestDTO bookCopy);
    void deleteId(UUID id);
}
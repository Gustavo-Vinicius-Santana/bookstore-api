package br.com.project_bookstore_api.bookstore_api.repository;

import br.com.project_bookstore_api.bookstore_api.model.BookCopy;
import br.com.project_bookstore_api.bookstore_api.model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, UUID> {
    long countByBookId(UUID bookId);
    long countByBookIdAndBookStatus(UUID bookId, BookStatus status);
}
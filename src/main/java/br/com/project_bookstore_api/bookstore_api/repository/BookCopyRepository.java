package br.com.project_bookstore_api.bookstore_api.repository;

import br.com.project_bookstore_api.bookstore_api.model.Author;
import br.com.project_bookstore_api.bookstore_api.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, UUID> {
    Optional<BookCopy> findByBook_TitleContainingIgnoreCase(String title);
}

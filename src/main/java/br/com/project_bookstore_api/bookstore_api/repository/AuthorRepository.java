package br.com.project_bookstore_api.bookstore_api.repository;

import br.com.project_bookstore_api.bookstore_api.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findByNameContaining(String name);
}

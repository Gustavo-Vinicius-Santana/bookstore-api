package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.model.Author;
import br.com.project_bookstore_api.bookstore_api.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceIml implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Page<Author> findAll(Pageable pagination) {
        return authorRepository.findAll(pagination);
    }

    @Override
    public Author findById(UUID id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado"));
    }

    @Override
    public Page<Author> findByName(String name, Pageable pagination) {
        return authorRepository.findByNameContaining(name, pagination);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author update(UUID id, Author author) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado"));

        existingAuthor.setName(author.getName());
        return authorRepository.save(existingAuthor);
    }

    @Override
    public void deleteId(UUID id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado"));
        authorRepository.delete(author);
    }
}
package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.model.Book;
import br.com.project_bookstore_api.bookstore_api.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceIml implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pagination) {
        return bookRepository.findAll(pagination);
    }

    @Override
    public Book findById(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));
    }

    @Override
    public Book findByName(String name) {
        return bookRepository.findByTitleContainingIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado pelo titulo."));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book update(UUID id, Book book) {
        var existing = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        // Atualiza apenas os campos necessários
        existing.setTitle(book.getTitle());
        existing.setDescription(book.getDescription());
        existing.setPageCount(book.getPageCount());
        existing.setAuthor(book.getAuthor());

        return bookRepository.save(existing);
    }

    @Override
    public void deleteId(UUID id) {
        var existing = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        bookRepository.delete(existing);
    }
}

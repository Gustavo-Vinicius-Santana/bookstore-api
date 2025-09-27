package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.dto.BookRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.BookResponseDTO;
import br.com.project_bookstore_api.bookstore_api.model.Book;
import br.com.project_bookstore_api.bookstore_api.repository.AuthorRepository;
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
    private final AuthorRepository authorRepository;

    @Override
    public Page<BookResponseDTO> findAll(Pageable pagination) {
        return bookRepository.findAll(pagination)
                .map(BookResponseDTO::new); // Converte cada Book para BookResponseDTO
    }

    @Override
    public BookResponseDTO findById(UUID id) {
        return bookRepository.findById(id)
                .map(BookResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));
    }

    @Override
    public Page<BookResponseDTO> findByTitle(String title, Pageable pagination) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pagination)
                .map(BookResponseDTO::new);
    }

    @Override
    @Transactional
    public BookResponseDTO save(BookRequestDTO bookRequestDTO) {
        var author = authorRepository.findById(bookRequestDTO.authorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado."));

        var book = Book.fromDto(bookRequestDTO);
        book.setAuthor(author);

        var savedBook = bookRepository.save(book);

        return new BookResponseDTO(savedBook);
    }

    @Transactional
    @Override
    public BookRequestDTO update(UUID id, BookRequestDTO bookRequestDTO) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        var author = authorRepository.findById(bookRequestDTO.authorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado."));

        book.setTitle(bookRequestDTO.title());
        book.setDescription(bookRequestDTO.description());
        book.setPageCount(bookRequestDTO.pageCount());
        book.setAuthor(author);

        return new BookRequestDTO(bookRepository.save(book));
    }

    @Override
    public void deleteId(UUID id) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));
        bookRepository.delete(existing);
    }
}
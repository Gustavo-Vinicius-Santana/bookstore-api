package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.dto.BookCopyRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.BookCopyResponseDTO;
import br.com.project_bookstore_api.bookstore_api.model.BookCopy;
import br.com.project_bookstore_api.bookstore_api.model.BookStatus;
import br.com.project_bookstore_api.bookstore_api.repository.BookCopyRepository;
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
public class BookCopyServiceIml implements BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    @Override
    public Page<BookCopyResponseDTO> findAll(Pageable pagination) {
        return bookCopyRepository.findAll(pagination)
                .map(BookCopyResponseDTO::new);
    }

    @Override
    public BookCopyResponseDTO findById(UUID id) {
        return bookCopyRepository.findById(id)
                .map(BookCopyResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Cópia do livro não encontrada"));
    }

    @Override
    public long countByBookId(UUID bookId) {
        return bookCopyRepository.countByBookId(bookId);
    }

    @Override
    public long countByBookIdAndStatus(UUID bookId, BookStatus status) {
        return bookCopyRepository.countByBookIdAndBookStatus(bookId, status);
    }

    @Override
    @Transactional
    public BookCopyResponseDTO save(BookCopyRequestDTO bookCopyRequestDTO) {
        var book = bookRepository.findById(bookCopyRequestDTO.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        var bookCopy = BookCopy.fromDto(bookCopyRequestDTO);
        bookCopy.setBook(book);

        var savedBookCopy = bookCopyRepository.save(bookCopy);

        return new BookCopyResponseDTO(savedBookCopy);
    }

    @Transactional
    @Override
    public BookCopyRequestDTO update(UUID id, BookCopyRequestDTO bookCopyRequestDTO) {
        var bookCopy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cópia do livro não encontrada"));

        var book = bookRepository.findById(bookCopyRequestDTO.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        bookCopy.setBook(book);
        bookCopy.setConditionStatus(bookCopyRequestDTO.conditionStatus());
        bookCopy.setBookStatus(bookCopyRequestDTO.bookStatus());

        return new BookCopyRequestDTO(bookCopyRepository.save(bookCopy));
    }

    @Override
    public void deleteId(UUID id) {
        BookCopy existing = bookCopyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cópia do livro não encontrada"));
        bookCopyRepository.delete(existing);
    }
}
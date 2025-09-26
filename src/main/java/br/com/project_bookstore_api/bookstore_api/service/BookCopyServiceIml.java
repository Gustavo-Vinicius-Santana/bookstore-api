package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.model.BookCopy;
import br.com.project_bookstore_api.bookstore_api.model.BookStatus;
import br.com.project_bookstore_api.bookstore_api.repository.BookCopyRepository;
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

    @Override
    public Page<BookCopy> findAll(Pageable pagination) {
        return bookCopyRepository.findAll(pagination);
    }

    @Override
    public BookCopy findById(UUID id) {
        return bookCopyRepository.findById(id)
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
    public BookCopy save(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    @Transactional
    @Override
    public BookCopy update(UUID id, BookCopy bookCopy) {
        BookCopy existing = bookCopyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cópia do livro não encontrada"));

        existing.setBook(bookCopy.getBook());
        existing.setConditionStatus(bookCopy.getConditionStatus());
        existing.setBookStatus(bookCopy.getBookStatus());

        return bookCopyRepository.save(existing);
    }

    @Override
    public void deleteId(UUID id) {
        BookCopy existing = bookCopyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cópia do livro não encontrada"));
        bookCopyRepository.delete(existing);
    }
}
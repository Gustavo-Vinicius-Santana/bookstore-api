package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.dto.LoanBookRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.LoanBookResponseDTO;
import br.com.project_bookstore_api.bookstore_api.model.BookCopy;
import br.com.project_bookstore_api.bookstore_api.model.LoanBook;
import br.com.project_bookstore_api.bookstore_api.model.User;
import br.com.project_bookstore_api.bookstore_api.model.BookStatus;
import br.com.project_bookstore_api.bookstore_api.repository.BookCopyRepository;
import br.com.project_bookstore_api.bookstore_api.repository.LoanBookRepository;
import br.com.project_bookstore_api.bookstore_api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanBookServiceIml implements LoanBookService {

    private final LoanBookRepository loanBookRepository;
    private final UserRepository userRepository;
    private final BookCopyRepository bookCopyRepository; // ✅ Novo repositório

    @Override
    public List<LoanBookResponseDTO> findAll() {
        return loanBookRepository.findAll()
                .stream()
                .map(LoanBook::toDto)
                .toList();
    }

    @Override
    public LoanBookResponseDTO findById(UUID id) {
        LoanBook loan = loanBookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado."));
        return loan.toDto();
    }

    @Transactional
    @Override
    public LoanBookResponseDTO save(LoanBookRequestDTO dto) {
        // Busca o usuário
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        // Busca a cópia do livro
        BookCopy bookCopy = bookCopyRepository.findById(dto.bookCopyId())
                .orElseThrow(() -> new EntityNotFoundException("Cópia do livro não encontrada."));

        // Verifica se a cópia está disponível
        if (bookCopy.getBookStatus() != BookStatus.AVAILABLE) {
            throw new IllegalStateException("Esta cópia não está disponível para empréstimo.");
        }

        // Verifica se o usuário já tem empréstimo ativo
        boolean available = loanBookRepository.isUserAvailableForNewLoan(user.getId());
        if (!available) {
            throw new IllegalStateException("Usuário já possui um empréstimo ativo.");
        }

        // Cria o empréstimo a partir do DTO
        LoanBook loan = LoanBook.fromDto(dto, user, bookCopy);

        // Salva
        LoanBook savedLoan = loanBookRepository.save(loan);
        return savedLoan.toDto();
    }

    @Transactional
    @Override
    public LoanBookResponseDTO update(UUID id, LoanBookRequestDTO dto) {
        LoanBook existingLoan = loanBookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado."));

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        BookCopy bookCopy = bookCopyRepository.findById(dto.bookCopyId())
                .orElseThrow(() -> new EntityNotFoundException("Cópia do livro não encontrada."));

        existingLoan.setDateHourLoan(dto.dateHourLoan());
        existingLoan.setDateHourLoanReturn(dto.dateHourLoanReturn());
        existingLoan.setUser(user);
        existingLoan.setBookCopy(bookCopy);

        LoanBook updatedLoan = loanBookRepository.save(existingLoan);
        return updatedLoan.toDto();
    }

    @Override
    public void deleteById(UUID id) {
        LoanBook existingLoan = loanBookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado."));
        loanBookRepository.delete(existingLoan);
    }

    @Override
    public List<LoanBookResponseDTO> findByDateHour(LocalDateTime start, LocalDateTime end) {
        return loanBookRepository.findLoansByDateHourLoanBetween(start, end)
                .stream()
                .map(LoanBook::toDto)
                .toList();
    }
}
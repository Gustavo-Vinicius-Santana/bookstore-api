package br.com.project_bookstore_api.bookstore_api.service;

import br.com.project_bookstore_api.bookstore_api.dto.LoanBookRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.LoanBookResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface LoanBookService {

    List<LoanBookResponseDTO> findAll();

    LoanBookResponseDTO findById(UUID id);

    LoanBookResponseDTO save(LoanBookRequestDTO dto);

    LoanBookResponseDTO update(UUID id, LoanBookRequestDTO dto);

    void deleteById(UUID id);

    List<LoanBookResponseDTO> findByDateHour(LocalDateTime start, LocalDateTime end);
}

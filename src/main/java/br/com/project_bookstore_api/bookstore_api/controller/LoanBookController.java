package br.com.project_bookstore_api.bookstore_api.controller;

import br.com.project_bookstore_api.bookstore_api.dto.LoanBookRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.LoanBookResponseDTO;
import br.com.project_bookstore_api.bookstore_api.service.LoanBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanBookController {

    private final LoanBookService loanBookService;

    @GetMapping
    public ResponseEntity<List<LoanBookResponseDTO>> findAll() {
        return ResponseEntity.ok(loanBookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanBookResponseDTO> findById(@PathVariable UUID id) {
        LoanBookResponseDTO loan = loanBookService.findById(id);
        return ResponseEntity.ok(loan);
    }

    @PostMapping
    public ResponseEntity<LoanBookResponseDTO> save(@RequestBody LoanBookRequestDTO dto) {
        LoanBookResponseDTO createdLoan = loanBookService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanBookResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody LoanBookRequestDTO dto
    ) {
        LoanBookResponseDTO updatedLoan = loanBookService.update(id, dto);
        return ResponseEntity.ok(updatedLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        loanBookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<LoanBookResponseDTO>> findByDateHour(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        List<LoanBookResponseDTO> loans = loanBookService.findByDateHour(start, end);
        return ResponseEntity.ok(loans);
    }
}

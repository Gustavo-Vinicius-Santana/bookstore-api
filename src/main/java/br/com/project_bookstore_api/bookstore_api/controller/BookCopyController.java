package br.com.project_bookstore_api.bookstore_api.controller;

import br.com.project_bookstore_api.bookstore_api.dto.BookCopyRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.BookCopyResponseDTO;
import br.com.project_bookstore_api.bookstore_api.model.BookStatus;
import br.com.project_bookstore_api.bookstore_api.service.BookCopyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookCopy")
@RequiredArgsConstructor
public class BookCopyController {

    private final BookCopyService bookCopyService;

    @GetMapping
    public ResponseEntity<Page<BookCopyResponseDTO>> findAll(
            @PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pagination) {
        return ResponseEntity.ok(bookCopyService.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCopyResponseDTO> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bookCopyService.findById(id));
    }

    @GetMapping("/quantidade")
    public ResponseEntity<Long> countByBookAndStatus(
            @RequestParam UUID bookId,
            @RequestParam(required = false) BookStatus status) {
        if (status != null) {
            return ResponseEntity.ok(bookCopyService.countByBookIdAndStatus(bookId, status));
        }
        return ResponseEntity.ok(bookCopyService.countByBookId(bookId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookCopyResponseDTO> save(@RequestBody BookCopyRequestDTO bookCopyRequestDTO) {
        BookCopyResponseDTO savedBookCopy = bookCopyService.save(bookCopyRequestDTO);
        return ResponseEntity.ok(savedBookCopy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookCopyRequestDTO> update(@PathVariable("id") UUID id, @RequestBody BookCopyRequestDTO bookCopyRequestDTO) {
        return ResponseEntity.ok(bookCopyService.update(id, bookCopyRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        bookCopyService.deleteId(id);
        return ResponseEntity.noContent().build();
    }
}
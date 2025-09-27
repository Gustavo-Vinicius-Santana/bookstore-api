package br.com.project_bookstore_api.bookstore_api.controller;

import br.com.project_bookstore_api.bookstore_api.dto.BookRequestDTO;
import br.com.project_bookstore_api.bookstore_api.dto.BookResponseDTO;
import br.com.project_bookstore_api.bookstore_api.model.Book;
import br.com.project_bookstore_api.bookstore_api.service.BookServiceIml;
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
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookServiceIml bookServiceIml;

    @GetMapping
    public ResponseEntity<Page<BookResponseDTO>> findAll(
            @PageableDefault(size = 5, sort = {"title"}, direction = Sort.Direction.ASC) Pageable pagination) {
        return ResponseEntity.ok(bookServiceIml.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bookServiceIml.findById(id));
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<Page<BookResponseDTO>> findByTitle(
            @PathVariable String title,
            @PageableDefault(size = 5) Pageable pagination) {
        return ResponseEntity.ok(bookServiceIml.findByTitle(title, pagination));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponseDTO> save(@RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO savedBook = bookServiceIml.save(bookRequestDTO);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookRequestDTO> update(@PathVariable("id") UUID id, @RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.ok(bookServiceIml.update(id, bookRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        bookServiceIml.deleteId(id);
        return ResponseEntity.noContent().build();
    }
}

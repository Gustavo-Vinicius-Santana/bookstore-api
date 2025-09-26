package br.com.project_bookstore_api.bookstore_api.controller;

import br.com.project_bookstore_api.bookstore_api.model.BookCopy;
import br.com.project_bookstore_api.bookstore_api.model.BookStatus;
import br.com.project_bookstore_api.bookstore_api.service.BookCopyServiceIml;
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

    private final BookCopyServiceIml bookCopyServiceIml;

    @GetMapping
    public ResponseEntity<Page<BookCopy>> findAll(
            @PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pagination) {
        return ResponseEntity.ok(bookCopyServiceIml.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCopy> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bookCopyServiceIml.findById(id));
    }

    @GetMapping("/quantidade")
    public ResponseEntity<Long> countByBookAndStatus(
            @RequestParam UUID bookId,
            @RequestParam(required = false) BookStatus status) {
        if (status != null) {
            return ResponseEntity.ok(bookCopyServiceIml.countByBookIdAndStatus(bookId, status));
        }
        return ResponseEntity.ok(bookCopyServiceIml.countByBookId(bookId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookCopy> save(@RequestBody BookCopy bookCopy) {
        BookCopy savedBookCopy = bookCopyServiceIml.save(bookCopy);
        return ResponseEntity.ok(savedBookCopy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookCopy> update(@PathVariable("id") UUID id, @RequestBody BookCopy bookCopy) {
        return ResponseEntity.ok(bookCopyServiceIml.update(id, bookCopy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        bookCopyServiceIml.deleteId(id);
        return ResponseEntity.noContent().build();
    }
}
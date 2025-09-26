package br.com.project_bookstore_api.bookstore_api.controller;

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
    public ResponseEntity<Page<Book>> findAll(
            @PageableDefault(size = 5, sort = {"title"}, direction = Sort.Direction.ASC) Pageable pagination) {
        return ResponseEntity.ok(bookServiceIml.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bookServiceIml.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Book>> findByTitle(
            @RequestParam String title,
            @PageableDefault(size = 5) Pageable pagination) {
        return ResponseEntity.ok(bookServiceIml.findByTitle(title, pagination));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> save(@RequestBody Book book) {
        Book savedBook = bookServiceIml.save(book);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable("id") UUID id, @RequestBody Book book) {
        return ResponseEntity.ok(bookServiceIml.update(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        bookServiceIml.deleteId(id);
        return ResponseEntity.noContent().build();
    }
}

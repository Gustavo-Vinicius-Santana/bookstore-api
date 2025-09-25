package br.com.project_bookstore_api.bookstore_api.controller;

import br.com.project_bookstore_api.bookstore_api.model.Author;
import br.com.project_bookstore_api.bookstore_api.service.AuthorServiceIml;
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
@RequestMapping("/api/author")
@RequiredArgsConstructor

public class AuthorController {
    private final AuthorServiceIml authorServiceIml;

    @GetMapping
    public ResponseEntity<Page<Author>> findAll(
            @PageableDefault(size = 5, sort = {"name"}, direction = Sort.Direction.ASC ) Pageable pagination){
        return ResponseEntity.ok(authorServiceIml.findAll(pagination));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Author> findByName(@PathVariable("name") String name){
        return ResponseEntity.ok(authorServiceIml.findByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(authorServiceIml.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> save(@RequestBody Author author) {
        return ResponseEntity.ok(authorServiceIml.save(author));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable("id") UUID id, @RequestBody Author author){
        return ResponseEntity.ok(authorServiceIml.update(id, author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> delete(@PathVariable("id") UUID id){
        authorServiceIml.deleteId(id);
        return ResponseEntity.noContent().build();
    }
}

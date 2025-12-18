package com.example.demo.controller;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.service.impl.AuthorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorServiceImpl authorService;

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO){
        return authorService.create(authorDTO);
    }

    @GetMapping
    public List<AuthorDTO> getAllAuthors(){
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public AuthorDTO getById(@PathVariable Long id){
        return authorService.getById(id);
    }

    @PutMapping("/{id}")
    public AuthorDTO updateById(@PathVariable Long id, @RequestBody AuthorDTO authorDTO){
        return authorService.update(id, authorDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        authorService.delete(id);
    }
}

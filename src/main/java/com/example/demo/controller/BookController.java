package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookServiceImpl bookService;

    @PostMapping
    public BookDTO create(@RequestBody BookDTO bookDTO){
        return bookService.create(bookDTO);
    }

    @GetMapping
    public List<BookDTO> getAllBooks(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable Long id){
        return bookService.getById(id);
    }

    @PutMapping("/{id}")
    public BookDTO updateById(@PathVariable Long id, @RequestBody BookDTO bookDTO){
        return bookService.update(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        bookService.delete(id);
    }
}

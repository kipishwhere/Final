package com.example.demo.service;

import com.example.demo.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAll();
    BookDTO getById(Long id);
    BookDTO create(BookDTO bookDTODTO);
    BookDTO update(Long id, BookDTO bookDTO);
    void delete(Long id);
}

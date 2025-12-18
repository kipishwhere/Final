package com.example.demo.service.impl;

import com.example.demo.dto.BookDTO;
import com.example.demo.mapper.BookMapper;
import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDTO create(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book saved = bookRepository.save(book);
        return bookMapper.toDto(saved);
    }

    public List<BookDTO> getAll() {
        return bookMapper.toDtoList(bookRepository.findAll());
    }

    @Transactional
    public BookDTO getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No book with this id: " + id));
        return bookMapper.toDto(book);
    }

    @Transactional
    public BookDTO update(Long id, BookDTO bookDTO) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No book with this id: " + id));
        existing.setTitle(bookDTO.getTitle());
        existing.setPrice(bookDTO.getPrice());
        Book updated = bookRepository.save(existing);
        return bookMapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("No book with this id: " + id);
        }
        bookRepository.deleteById(id);
    }
}

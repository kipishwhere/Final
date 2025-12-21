package com.example.demo.service.impl;

import com.example.demo.dto.BookDTO;
import com.example.demo.mapper.BookMapper;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.mapper.PublisherMapper;
import com.example.demo.models.Book;
import com.example.demo.models.Author;
import com.example.demo.models.Publisher;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;
import com.example.demo.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final PublisherMapper publisherMapper;
    private final AuthorMapper authorMapper;

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

        Publisher publisher = publisherRepository.findById(bookDTO.getPublisher().getId())
                .orElseGet(() -> publisherMapper.toEntity(bookDTO.getPublisher()));
        existing.setPublisher(publisher);

        List<Author> authors = bookDTO.getAuthors().stream()
                .map(a -> authorRepository.findById(a.getId()).orElse(authorMapper.toEntity(a)))
                .collect(Collectors.toList());
        existing.setAuthors(authors);

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

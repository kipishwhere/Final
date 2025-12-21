package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@ActiveProfiles("test")
@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    void getAllTest() {
        bookRepository.save(new Book(null, "Book 1", 10.0, null, null));
        bookRepository.save(new Book(null, "Book 2", 15.0, null, null));

        List<BookDTO> result = bookService.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.size() >= 2);

        for (BookDTO dto : result) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getTitle());
            Assertions.assertNotNull(dto.getPrice());
        }
    }

    @Test
    @Transactional
    void getByIdTest() {
        Book saved = bookRepository.save(new Book(null, "Book 3", 10.0, null, null));

        BookDTO dto = bookService.getById(saved.getId());

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(saved.getId(), dto.getId());
        Assertions.assertEquals(saved.getTitle(), dto.getTitle());
        Assertions.assertEquals(saved.getPrice(), dto.getPrice());

        Assertions.assertThrows(RuntimeException.class, () -> bookService.getById(-1L));
    }

    @Test
    @Transactional
    void createTest() {
        BookDTO bookDTO = new BookDTO(null, "New Book", 20.0, null, null);

        BookDTO created = bookService.create(bookDTO);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals(bookDTO.getTitle(), created.getTitle());
        Assertions.assertEquals(bookDTO.getPrice(), created.getPrice());
    }

    @Test
    @Transactional
    void updateTest() {
        Book saved = bookRepository.save(new Book(null, "Old book", 10.0, null, null));

        BookDTO updatedDto = new BookDTO(saved.getId(), "Updated book", 25.0, null, null);

        BookDTO updated = bookService.update(saved.getId(), updatedDto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(updatedDto.getTitle(), updated.getTitle());
        Assertions.assertEquals(updatedDto.getPrice(), updated.getPrice());
    }

    @Test
    @Transactional
    void deleteTest() {
        Book saved = bookRepository.save(new Book(null, "To Delete", 10.0, null, null));

        bookService.delete(saved.getId());

        Assertions.assertThrows(RuntimeException.class, () -> bookService.getById(saved.getId()));
    }
}

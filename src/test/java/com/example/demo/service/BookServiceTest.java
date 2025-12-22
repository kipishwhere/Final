package com.example.demo.service;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.BookDTO;
import com.example.demo.dto.PublisherDTO;
import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.models.Publisher;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;
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

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @Transactional
    void getAllTest() {
        Publisher publisher = publisherRepository.save(new Publisher(null, "Test Pub", "UK", null));
        Author author = authorRepository.save(new Author(null, "Author", 40,null));

        bookRepository.save(new Book(null, "Book 1", 10.0, publisher, List.of(author)));
        bookRepository.save(new Book(null, "Book 2", 15.0, publisher, List.of(author)));

        List<BookDTO> result = bookService.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.size() >= 2);
    }

    @Test
    @Transactional
    void getByIdTest() {
        Publisher publisher = publisherRepository.save(new Publisher(null, "Test Pub", "UK",null));
        Author author = authorRepository.save(new Author(null, "Author", 40,null));

        Book saved = bookRepository.save(new Book(null, "Book 3", 10.0, publisher, List.of(author)));

        BookDTO dto = bookService.getById(saved.getId());

        Assertions.assertEquals(saved.getId(), dto.getId());
        Assertions.assertThrows(RuntimeException.class, () -> bookService.getById(-1L));
    }

    @Test
    @Transactional
    void createTest() {
        Publisher publisher = publisherRepository.save(new Publisher(null, "Create Pub", "USA", null));
        Author author = authorRepository.save(new Author(null, "Create Author", 50,null));

        BookDTO bookDTO = new BookDTO(
                null,
                "New Book",
                20.0,
                new PublisherDTO(publisher.getId(), publisher.getName(), publisher.getCountry()),
                List.of(new AuthorDTO(author.getId(), author.getName(), author.getAge()))
        );

        BookDTO created = bookService.create(bookDTO);

        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals("New Book", created.getTitle());
    }

    @Test
    @Transactional
    void updateTest() {
        Publisher oldPublisher = publisherRepository.save(new Publisher(null, "Old Pub", "UK", null));
        Author oldAuthor = authorRepository.save(new Author(null, "Old Author", 60, null));

        Book saved = bookRepository.save(
                new Book(null, "Old book", 10.0, oldPublisher, List.of(oldAuthor))
        );

        Publisher newPublisher = publisherRepository.save(new Publisher(null, "New Pub", "US", null));
        Author newAuthor = authorRepository.save(new Author(null, "New Author", 45, null));

        BookDTO updatedDto = new BookDTO(
                saved.getId(),
                "Updated book",
                25.0,
                new PublisherDTO(newPublisher.getId(), newPublisher.getName(), newPublisher.getCountry()),
                List.of(new AuthorDTO(newAuthor.getId(), newAuthor.getName(), newAuthor.getAge()))
        );

        BookDTO updated = bookService.update(saved.getId(), updatedDto);

        Assertions.assertEquals("Updated book", updated.getTitle());
        Assertions.assertEquals(25.0, updated.getPrice());
        Assertions.assertEquals("New Pub", updated.getPublisher().getName());
        Assertions.assertEquals(1, updated.getAuthors().size());
    }

    @Test
    @Transactional
    void deleteTest() {
        Publisher publisher = publisherRepository.save(new Publisher(null, "Del Pub", "UK",null));
        Author author = authorRepository.save(new Author(null, "Del Author", 30,null));

        Book saved = bookRepository.save(
                new Book(null, "To Delete", 10.0, publisher, List.of(author))
        );

        bookService.delete(saved.getId());

        Assertions.assertThrows(RuntimeException.class, () -> bookService.getById(saved.getId()));
    }
}

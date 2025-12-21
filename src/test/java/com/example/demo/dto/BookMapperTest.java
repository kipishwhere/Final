package com.example.demo.dto;

import com.example.demo.mapper.BookMapper;
import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.models.Publisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class BookMapperTest {
    @Autowired
    private BookMapper bookMapper;

    @Test
    void convertEntityToDto() {
        Publisher publisher = new Publisher(1L, "Bloomsbury", "UK", null);
        Author author1 = new Author(1L, "J.K. Rowling", 55, null);
        Author author2 = new Author(2L, "Stephen King", 73, null);
        Book book = new Book(1L, "Harry Potter", 39.99, publisher, List.of(author1, author2));

        BookDTO dto = bookMapper.toDto(book);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(book.getId(), dto.getId());
        Assertions.assertEquals(book.getTitle(), dto.getTitle());
        Assertions.assertEquals(book.getPrice(), dto.getPrice());
        Assertions.assertNotNull(dto.getPublisher());
        Assertions.assertEquals(book.getPublisher().getName(), dto.getPublisher().getName());
        Assertions.assertEquals(2, dto.getAuthors().size());
    }

    @Test
    void convertDtoToEntity() {
        PublisherDTO publisherDTO = new PublisherDTO(2L, "Penguin", "USA");
        AuthorDTO authorDTO1 = new AuthorDTO(3L, "George R.R. Martin", 75);
        AuthorDTO authorDTO2 = new AuthorDTO(4L, "Agatha Christie", 85);
        BookDTO dto = new BookDTO(2L, "A Game of Thrones", 29.99, publisherDTO, List.of(authorDTO1, authorDTO2));

        Book book = bookMapper.toEntity(dto);

        Assertions.assertNotNull(book);
        Assertions.assertEquals(dto.getId(), book.getId());
        Assertions.assertEquals(dto.getTitle(), book.getTitle());
        Assertions.assertEquals(dto.getPrice(), book.getPrice());
        Assertions.assertNotNull(book.getPublisher());
        Assertions.assertEquals("Penguin", book.getPublisher().getName());
        Assertions.assertEquals(2, book.getAuthors().size());
    }

    @Test
    void convertEntityListToDtoList() {
        Book book1 = new Book(1L, "Harry Potter", 39.99, new Publisher(1L, "Bloomsbury", "UK",  null), new ArrayList<>());
        Book book2 = new Book(2L, "A Game of Thrones", 29.99, new Publisher(2L, "Penguin", "USA", null), new ArrayList<>());
        List<Book> books = List.of(book1, book2);

        List<BookDTO> dtos = bookMapper.toDtoList(books);

        Assertions.assertNotNull(dtos);
        Assertions.assertEquals(books.size(), dtos.size());
    }
}

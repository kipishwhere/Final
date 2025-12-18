package com.example.demo.mapper;

import com.example.demo.dto.BookDTO;
import com.example.demo.models.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PublisherMapper.class, AuthorMapper.class} )
public interface BookMapper {
    BookDTO toDto(Book book);
    Book toEntity(BookDTO bookDTO);

    List<BookDTO> toDtoList(List<Book> books);
}

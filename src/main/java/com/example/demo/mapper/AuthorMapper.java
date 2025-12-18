package com.example.demo.mapper;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.models.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDto(Author author);
    Author toEntity(AuthorDTO authorDTO);

    List<AuthorDTO> toDtoList(List<Author> authors);
}

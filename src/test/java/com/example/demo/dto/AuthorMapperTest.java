package com.example.demo.dto;

import com.example.demo.mapper.AuthorMapper;
import com.example.demo.models.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class AuthorMapperTest {
    @Autowired
    private AuthorMapper authorMapper;

    @Test
    void convertEntityToDto(){
        Author author = new Author(1L, "Adilbek", 19, null);

        AuthorDTO authorDTO = authorMapper.toDto(author);

        Assertions.assertNotNull(authorDTO);
        Assertions.assertEquals(author.getId(), authorDTO.getId());
        Assertions.assertEquals(author.getName(), authorDTO.getName());
        Assertions.assertEquals(author.getAge(), authorDTO.getAge());
    }

    @Test
    void convertDtoToEntity(){
        AuthorDTO authorDTO = new AuthorDTO(2L, "Sanzhar", 20);

        Author author = authorMapper.toEntity(authorDTO);

        Assertions.assertNotNull(author);
        Assertions.assertEquals(authorDTO.getId(), author.getId());
        Assertions.assertEquals(authorDTO.getName(), author.getName());
        Assertions.assertEquals(authorDTO.getAge(), author.getAge());
    }

    @Test
    void convertEntityListToDtoList() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1L, "Yersultan", 19,null));
        authors.add(new Author(2L, "Daulet", 20, null));

        List<AuthorDTO> authorDTOList = authorMapper.toDtoList(authors);

        Assertions.assertNotNull(authorDTOList);
        Assertions.assertEquals(authors.size(), authorDTOList.size());

        for (int i = 0; i < authors.size(); i++) {
            Author author = authors.get(i);
            AuthorDTO authorDTO = authorDTOList.get(i);
            Assertions.assertEquals(author.getId(), authorDTO.getId());
            Assertions.assertEquals(author.getName(), authorDTO.getName());
            Assertions.assertEquals(author.getAge(), authorDTO.getAge());
        }
    }
}

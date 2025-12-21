package com.example.demo.service;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.models.Author;
import com.example.demo.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@ActiveProfiles("test")
@SpringBootTest
public class AuthorServiceTest {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @Transactional
    void getAllTest(){
        authorRepository.save(new Author(null, "Yersultan", 19, null));
        authorRepository.save(new Author(null, "Adilbek", 20, null));

        List<AuthorDTO> result = authorService.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.size()>=2);

        for(AuthorDTO dto : result){
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
            Assertions.assertNotNull(dto.getAge());
        }
    }

    @Test
    @Transactional
    void getByIdTest(){
        Author saved = authorRepository.save(new Author(null, "Yersultan", 19, null));

        AuthorDTO dto = authorService.getById(saved.getId());

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getId(), saved.getId());
        Assertions.assertEquals(dto.getName(), saved.getName());
        Assertions.assertEquals(dto.getAge(), saved.getAge());

        Assertions.assertThrows(RuntimeException.class, () -> authorService.getById(-1L));
    }

    @Test
    @Transactional
    void createTest(){
        AuthorDTO input = new AuthorDTO(null, "Shyngys", 25);

        AuthorDTO created = authorService.create(input);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals(input.getName(), created.getName());
        Assertions.assertEquals(input.getAge(), created.getAge());
    }

    @Test
    @Transactional
    void updateTest(){
        Author saved = authorRepository.save(new Author(null, "Yersultan", 26, null));

        AuthorDTO updatedDto = new AuthorDTO(saved.getId(), "New name", 24);

        AuthorDTO updated = authorService.update(saved.getId(), updatedDto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(updated.getName(), updatedDto.getName());
        Assertions.assertEquals(updated.getAge(), updatedDto.getAge());
    }

    @Test
    @Transactional
    void deleteTest(){
        Author saved = authorRepository.save(new Author(null, "To delete", 21, null));

        authorService.delete(saved.getId());

        Assertions.assertThrows(RuntimeException.class, ()-> authorService.getById(saved.getId()));
    }
}

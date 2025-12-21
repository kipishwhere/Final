package com.example.demo.service;

import com.example.demo.dto.PublisherDTO;
import com.example.demo.models.Publisher;
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
public class PublisherServiceTest {
    @Autowired
    private PublisherService publisherService;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    @Transactional
    void getAllTest() {
        publisherRepository.save(new Publisher(null, "Publisher 1", "USA", null));
        publisherRepository.save(new Publisher(null, "Publisher 2", "UK", null));

        List<PublisherDTO> result = publisherService.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.size() >= 2);

        for (PublisherDTO dto : result) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
            Assertions.assertNotNull(dto.getCountry());
        }
    }

    @Test
    @Transactional
    void getByIdTest() {
        Publisher saved = publisherRepository.save(new Publisher(null, "Test Publisher", "Canada", null));

        PublisherDTO dto = publisherService.getById(saved.getId());

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(saved.getId(), dto.getId());
        Assertions.assertEquals(saved.getName(), dto.getName());
        Assertions.assertEquals(saved.getCountry(), dto.getCountry());

        Assertions.assertThrows(RuntimeException.class, () -> publisherService.getById(-1L));
    }

    @Test
    @Transactional
    void createTest() {
        PublisherDTO input = new PublisherDTO(null, "New Publisher", "Germany");

        PublisherDTO created = publisherService.create(input);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals(input.getName(), created.getName());
        Assertions.assertEquals(input.getCountry(), created.getCountry());
    }

    @Test
    @Transactional
    void updateTest() {
        Publisher saved = publisherRepository.save(new Publisher(null, "Old Publisher", "France", null));

        PublisherDTO updateDTO = new PublisherDTO(saved.getId(), "Updated Publisher", "Spain");

        PublisherDTO updated = publisherService.update(saved.getId(), updateDTO);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(updateDTO.getName(), updated.getName());
        Assertions.assertEquals(updateDTO.getCountry(), updated.getCountry());
    }

    @Test
    @Transactional
    void deleteTest() {
        Publisher saved = publisherRepository.save(new Publisher(null, "To Delete", "Italy", null));

        publisherService.delete(saved.getId());

        Assertions.assertThrows(RuntimeException.class, () -> publisherService.getById(saved.getId()));
    }
}

package com.example.demo.dto;

import com.example.demo.mapper.PublisherMapper;
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
public class PublisherMapperTest {
    @Autowired
    private PublisherMapper publisherMapper;

    @Test
    void convertEntityToDto() {
        Publisher publisher = new Publisher(1L, "Bloomsbury", "UK", null);

        PublisherDTO publisherDTO = publisherMapper.toDto(publisher);

        Assertions.assertNotNull(publisherDTO);
        Assertions.assertEquals(publisher.getId(), publisherDTO.getId());
        Assertions.assertEquals(publisher.getName(), publisherDTO.getName());
        Assertions.assertEquals(publisher.getCountry(), publisherDTO.getCountry());
    }

    @Test
    void convertDtoToEntity() {
        PublisherDTO publisherDTO = new PublisherDTO(1L, "Bloomsbury", "UK");

        Publisher publisher = publisherMapper.toEntity(publisherDTO);

        Assertions.assertNotNull(publisher);
        Assertions.assertEquals(publisherDTO.getId(), publisher.getId());
        Assertions.assertEquals(publisherDTO.getName(), publisher.getName());
        Assertions.assertEquals(publisherDTO.getCountry(), publisher.getCountry());
    }

    @Test
    void convertEntityListToDtoList() {
        List<Publisher> publishers = new ArrayList<>();
        publishers.add(new Publisher(1L, "Bloomsbury", "UK", null));
        publishers.add(new Publisher(2L, "Penguin", "USA", null));

        List<PublisherDTO> publisherDTOList = publisherMapper.toDtoList(publishers);

        Assertions.assertNotNull(publisherDTOList);
        Assertions.assertEquals(publishers.size(), publisherDTOList.size());

        for (int i = 0; i < publishers.size(); i++) {
            Publisher publisher = publishers.get(i);
            PublisherDTO publisherDTO = publisherDTOList.get(i);
            Assertions.assertEquals(publisher.getId(), publisherDTO.getId());
            Assertions.assertEquals(publisher.getName(), publisherDTO.getName());
            Assertions.assertEquals(publisher.getCountry(), publisherDTO.getCountry());
        }
    }

}

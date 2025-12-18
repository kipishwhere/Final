package com.example.demo.mapper;

import com.example.demo.dto.PublisherDTO;
import com.example.demo.models.Publisher;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    PublisherDTO toDto(Publisher publisher);
    Publisher toEntity(PublisherDTO publisherDTO);

    List<PublisherDTO> toDtoList(List<Publisher> publishers);
}

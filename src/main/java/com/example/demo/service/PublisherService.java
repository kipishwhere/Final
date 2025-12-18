package com.example.demo.service;

import com.example.demo.dto.PublisherDTO;

import java.util.List;

public interface PublisherService {
    List<PublisherDTO> getAll();
    PublisherDTO getById(Long id);
    PublisherDTO create(PublisherDTO publisherDTO);
    PublisherDTO update(Long id, PublisherDTO publisherDTO);
    void delete(Long id);
}

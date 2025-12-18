package com.example.demo.service.impl;

import com.example.demo.dto.PublisherDTO;
import com.example.demo.mapper.PublisherMapper;
import com.example.demo.models.Publisher;
import com.example.demo.repository.PublisherRepository;
import com.example.demo.service.PublisherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherDTO create(PublisherDTO publisherDTO){
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        Publisher saved = publisherRepository.save(publisher);
        return publisherMapper.toDto(saved);
    }

    public List<PublisherDTO> getAll(){
        return publisherMapper.toDtoList(publisherRepository.findAll());
    }

    @Transactional
    public PublisherDTO getById(Long id){
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No publisher with this id" + id));

        return publisherMapper.toDto(publisher);
    }

    public PublisherDTO update(Long id, PublisherDTO publisherDTO){
        Publisher existing = publisherRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No publisher with this id" + id));
        existing.setName(publisherDTO.getName());
        existing.setCountry(publisherDTO.getCountry());
        Publisher updated = publisherRepository.save(existing);

        return publisherMapper.toDto(updated);
    }

    public void delete(Long id){
        publisherRepository.deleteById(id);
    }
}

package com.example.demo.service.impl;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.models.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorDTO create(AuthorDTO authorDTO){
        Author author = authorMapper.toEntity(authorDTO);
        Author saved = authorRepository.save(author);

        return authorMapper.toDto(saved);
    }

    public List<AuthorDTO> getAll(){
        return authorMapper.toDtoList(authorRepository.findAll());
    }

    @Transactional
    public AuthorDTO getById(Long id){
        return authorMapper.toDto(authorRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No Author with this id" + id)));
    }

    public AuthorDTO update(Long id, AuthorDTO authorDTO){
        Author existing = authorRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No Author with this id" + id));
        existing.setName(authorDTO.getName());
        existing.setAge(authorDTO.getAge());
        Author updated = authorRepository.save(existing);

        return authorMapper.toDto(updated);
    }

    public void delete(Long id){
        authorRepository.deleteById(id);
    }

}

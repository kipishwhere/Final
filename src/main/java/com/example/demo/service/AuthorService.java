package com.example.demo.service;

import com.example.demo.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAll();
    AuthorDTO getById(Long id);
    AuthorDTO create(AuthorDTO authorDTO);
    AuthorDTO update(Long id, AuthorDTO authorDTO);
    void delete(Long id);
}

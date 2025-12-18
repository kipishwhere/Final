package com.example.demo.controller;

import com.example.demo.dto.PublisherDTO;
import com.example.demo.service.impl.PublisherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/publishers")
public class PublisherController {
    private final PublisherServiceImpl publisherService;

    @PostMapping
    public PublisherDTO createPublishers(@RequestBody PublisherDTO publisherDTO){
        return publisherService.create(publisherDTO);
    }

    @GetMapping
    public List<PublisherDTO> getAllPublishers(){
        return publisherService.getAll();
    }

    @GetMapping("/{id}")
    public PublisherDTO getById(@PathVariable Long id){
        return publisherService.getById(id);
    }

    @PutMapping("/{id}")
    public PublisherDTO updateById(@PathVariable Long id, @RequestBody PublisherDTO publisherDTO){
        return publisherService.update(id, publisherDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        publisherService.delete(id);
    }
}

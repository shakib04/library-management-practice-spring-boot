package com.brac.its.libraryManagement.controller;

import com.brac.its.libraryManagement.model.Publisher;
import com.brac.its.libraryManagement.repository.PublisherRepository;
import com.brac.its.libraryManagement.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {

    private final PublisherRepository publisherRepository;

    @Autowired
    PublisherService publisherService;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("")
    public List<Publisher> getAllPublisher(){
       return publisherService.getAllPublisher();
    }

    @GetMapping("/{id}")
    public Optional<Publisher> getById(@PathVariable Long id){
        return publisherRepository.findById(id);
    }

    @PostMapping("")
    public Publisher create(@RequestBody Publisher publisher){
        return publisherRepository.save(publisher);
    }

    @PutMapping("")
    public Publisher update(@RequestBody Publisher publisher){
        return publisherRepository.save(publisher);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        publisherRepository.deleteById(id);
    }


}

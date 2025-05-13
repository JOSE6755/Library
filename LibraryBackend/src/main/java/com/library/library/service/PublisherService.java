package com.library.library.service;

import com.library.library.dto.PublisherDTO;
import com.library.library.entity.Publisher;
import com.library.library.mapper.PublisherMapper;
import com.library.library.repository.PublisherRepository;
import com.library.library.util.PageMetaInfo;
import com.library.library.util.Response;
import com.library.library.util.ResponseGenerator;
import com.library.library.util.SortPropertyValidator;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository)
    {
        this.publisherRepository = publisherRepository;
    }

    public ResponseEntity<Response<List<PublisherDTO>>> findAllPublisher(String filter, String sortDirection, int page, int size)
    {
        if (!SortPropertyValidator.validate("name", Publisher.class)) {

            return ResponseGenerator.generateResponseEntity(
                    "Invalid sort property name", HttpStatus.BAD_REQUEST, false, null, null, null);
        }
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.fromString(sortDirection), "name"));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Publisher> data = publisherRepository.findByNameAndPhoneAndEmail(filter, pageable);
        Page<PublisherDTO> publishers = data.map(PublisherMapper::toPublisherDTO);
        PageMetaInfo pageMetaInfo = new PageMetaInfo(
                publishers.getPageable().getPageNumber() + 1, publishers.getSize(),
                ((int) publishers.getTotalElements()), publishers.getTotalPages());
        return ResponseGenerator.generateResponseEntity(
                "All publishers obtained", HttpStatus.OK, true, publishers.getContent(),
                null, pageMetaInfo);
    }


    public ResponseEntity<Response<PublisherDTO>> createPublisher(PublisherDTO publisher)
    {

        if (publisherRepository.existsByEmail(publisher.email())) {
            return ResponseGenerator.generateResponseEntity(
                    "The email specified already exists", HttpStatus.BAD_REQUEST, false,
                    null, null, null);
        }
        Publisher newPublisher = PublisherMapper.toPublisher(publisher);
        publisherRepository.save(newPublisher);
        return ResponseGenerator.generateResponseEntity(
                "Publisher created successfully", HttpStatus.CREATED, true, publisher,
                null, null);
    }

    public ResponseEntity<Response<PublisherDTO>> updatePublisher(int id, PublisherDTO publisher)
    {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isEmpty()) {
            return ResponseGenerator.generateResponseEntity("Publisher not found", HttpStatus.NOT_FOUND, false, null, null, null);
        }
        Optional<Publisher> publisherByEmail = publisherRepository.findByEmail(publisher.email());
        if (publisherByEmail.isPresent() && publisherByEmail.get().getPublisherId() != id) {
            return ResponseGenerator.generateResponseEntity(
                    "The email specified already exists", HttpStatus.BAD_REQUEST, false,
                    null, null, null);
        }
        Publisher updatedPublisher = PublisherMapper.toPublisher(publisher);
        updatedPublisher.setPublisherId(id);
        publisherRepository.save(updatedPublisher);
        return ResponseGenerator.generateResponseEntity(
                "Publisher updated successfully", HttpStatus.OK, true, publisher, null,
                null);
    }

    public ResponseEntity<Response<Void>> deletePublisher(int id)
    {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isEmpty()) {
            return ResponseGenerator.generateResponseEntity("Publisher not found", HttpStatus.NOT_FOUND, false, null, null, null);
        }
        publisherRepository.deleteById(id);
        return ResponseGenerator.generateResponseEntity(
                String.format("Publisher with id: %s has been deleted", id), HttpStatus.OK, true, null, null, null);
    }
}

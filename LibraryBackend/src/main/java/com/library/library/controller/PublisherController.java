package com.library.library.controller;

import com.library.library.dto.PublisherDTO;
import com.library.library.service.PublisherService;
import com.library.library.util.Response;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/publisher")
@Validated
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService)
    {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<Response<List<PublisherDTO>>> publishers(
            @Size(max = 500) @RequestParam(required = false, name = "filter", defaultValue = "") String filter,
            @Pattern(regexp = "(asc|desc)", message = "sort must be either asc or desc")
            @RequestParam(required = false, name = "sort", defaultValue = "asc") String sort,
            @PositiveOrZero(message = "page must be greater than or equeal to 0")
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @Positive(message = "size must be a positive number") @Min(value = 5, message = "size must be at least 5")
            @Max(value = 20, message = "size must be less than or equal to 20")
            @RequestParam(required = false, name = "size", defaultValue = "5") int size)
    {
        return publisherService.findAllPublisher(filter, sort, page, size);
    }

    @PostMapping
    public ResponseEntity<Response<PublisherDTO>> publisher(@Valid @RequestBody PublisherDTO publisherDTO)
    {
        return publisherService.createPublisher(publisherDTO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response<PublisherDTO>> updatePublisher(
            @Positive(message = "The id must be a positive number") @PathVariable(name = "id") int id,
            @Valid @RequestBody PublisherDTO publisherDTO)
    {
        return publisherService.updatePublisher(id, publisherDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response<Void>> deletePublisher(
            @Positive(message = "The id must be a positive number") @PathVariable(name = "id") int id)
    {
        return publisherService.deletePublisher(id);
    }
}

package com.library.library.mapper;

import com.library.library.dto.PublisherDTO;
import com.library.library.entity.Publisher;

public class PublisherMapper {

    public static Publisher toPublisher(final PublisherDTO publisher) {
        Publisher newPublisher = new Publisher();
        newPublisher.setName(publisher.name());
        newPublisher.setAddress(publisher.address());
        newPublisher.setEmail(publisher.email());
        newPublisher.setPhone(publisher.phone());
        if (publisher.website() != null) newPublisher.setWebsite(publisher.website());
        return newPublisher;
    }

    public static PublisherDTO toPublisherDTO(final Publisher publisher) {
        if (publisher.getWebsite() != null) {
            return new PublisherDTO(publisher.getPublisherId(), publisher.getName(), publisher.getAddress(), publisher.getPhone(), publisher.getEmail(), publisher.getWebsite());
        }
        return new PublisherDTO(publisher.getPublisherId(), publisher.getName(), publisher.getAddress(), publisher.getPhone(), publisher.getEmail(), null);

    }
}

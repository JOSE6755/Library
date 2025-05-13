package com.library.library.repository;

import com.library.library.entity.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    boolean existsByEmail(String email);

    Optional<Publisher> findByEmail(String email);

    @Query("SELECT p FROM Publisher p WHERE p.name LIKE CONCAT('%', :filter, '%') OR p.phone LIKE CONCAT" +
           "('%', :filter, '%') OR p.email LIKE CONCAT('%', :filter, '%')")
    Page<Publisher> findByNameAndPhoneAndEmail(@Param("filter") String filter, Pageable pageable);

}

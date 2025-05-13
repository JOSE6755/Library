package com.library.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "publisher")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int publisherId;

    @Column(nullable = false,length = 500)

    private String name;

    @Column(nullable = false,length = 1000)

    private String address;


    @Column(nullable = false,length = 8)
    private String phone;


    @Column(nullable = false,length = 200)
    private String email;


    @Column(nullable = true,length = 500)
    private String website;
}

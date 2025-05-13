package com.library.library.dto;

import jakarta.validation.constraints.*;

public record PublisherDTO(
        @Min(1)
        Integer id,
        @NotEmpty
        @Size(max = 500)
        @Pattern(regexp = "^[a-zA-Z](?: [a-zA-Z]|[a-zA-Z])*", message = "name must contain only letters")
        String name,
        @NotEmpty
        @Size(max = 1000)
        String address,
        @NotEmpty
        @Size(min = 8, max = 8)
        @Pattern(regexp = "^\\d$", message = "phone must be only positive numbers")
        String phone,
        @Size(max = 200)
        @NotEmpty
        @Email(message = "email must be a valid email")
        String email,
        @Size(max = 500)
        //@Pattern(regexp = "^(https?://)?(www\\.)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        String website

) {

}

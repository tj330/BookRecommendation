package com.tj330.bookservice.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateBookRequest(
        @NotBlank(message = "Title name is required") String title,
        @NotBlank(message = "Author name is required") String author,
        @NotBlank(message = "Genre is required") String genre,
        @NotBlank(message = "Description is required") String description,
        @NotBlank(message = "Username is required") String username
) {
}

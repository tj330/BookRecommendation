package com.tj330.bookservice.dto;

import java.util.Date;

public record BookDto(
        Long id,
        String title,
        String author,
        String genre,
        String description,
        String createdBy,
        Date createdAt
) {
}

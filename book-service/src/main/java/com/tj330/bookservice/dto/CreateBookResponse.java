package com.tj330.bookservice.dto;

import java.util.Date;

public record CreateBookResponse(
        Long id,
        String title,
        String author,
        String genre,
        String createdBy,
        Date createdAt
) {
}

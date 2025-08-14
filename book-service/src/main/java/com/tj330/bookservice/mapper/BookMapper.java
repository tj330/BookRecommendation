package com.tj330.bookservice.mapper;

import com.tj330.bookservice.dto.BookDto;
import com.tj330.bookservice.dto.CreateBookRequest;
import com.tj330.bookservice.dto.CreateBookResponse;
import com.tj330.bookservice.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getDescription(),
                book.getUsername(),
                book.getCreatedAt()
        );
    }

    public Book toEntity(CreateBookRequest request) {
        return Book.builder()
                .title(request.title())
                .author(request.author())
                .description(request.description())
                .username(request.username())
                .build();
    }

    public CreateBookResponse toResponse(Book book) {
        return new  CreateBookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getUsername(),
                book.getCreatedAt()
        );
    }
}

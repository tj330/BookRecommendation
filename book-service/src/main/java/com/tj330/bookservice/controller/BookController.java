package com.tj330.bookservice.controller;

import com.tj330.bookservice.dto.BookDto;
import com.tj330.bookservice.dto.CreateBookRequest;
import com.tj330.bookservice.dto.CreateBookResponse;
import com.tj330.bookservice.dto.PagedResult;
import com.tj330.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return bookService.updateBook(id, bookDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedResult<BookDto> getBooks(@RequestParam(name = "page", defaultValue = "0") Integer page) {
        return bookService.getBooks(page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBookResponse createBook(CreateBookRequest request) {
        return bookService.createBook(request);
    }
}

package com.tj330.bookservice.service;

import com.tj330.bookservice.ApplicationProperties;
import com.tj330.bookservice.dto.BookDto;
import com.tj330.bookservice.exception.BookNotFoundException;
import com.tj330.bookservice.repository.BookRepository;
import com.tj330.bookservice.dto.CreateBookRequest;
import com.tj330.bookservice.dto.CreateBookResponse;
import com.tj330.bookservice.dto.PagedResult;
import com.tj330.bookservice.entity.Book;
import com.tj330.bookservice.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final ApplicationProperties properties;

    public void deleteBookById(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new BookNotFoundException(e.getMessage());
        }
    }

    public BookDto updateBook(Long id,BookDto bookDto) {
        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            Book book = existingBook.get();

            book.setTitle(bookDto.title());
            book.setAuthor(bookDto.author());
            book.setGenre(bookDto.genre());
            book.setDescription(bookDto.description());
            book.setUsername(bookDto.createdBy());

            bookRepository.save(book);

            return bookMapper.toDto(book);
        } else {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
    }

    public BookDto getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            return bookMapper.toDto(book.get());

        } else {
            throw new BookNotFoundException("Book not found with id: " + id);
        }

    }

    public PagedResult<BookDto> getBooks(int pageNo) {
        Sort sort = Sort.by("genre");
        Pageable pageable = PageRequest.of(pageNo, properties.pageSize(), sort);
        Page<BookDto> booksPage = bookRepository.findAll(pageable).map(bookMapper::toDto);

        return new PagedResult<>(
                booksPage.getContent(),
                booksPage.getTotalElements(),
                booksPage.getNumber(),
                booksPage.getTotalPages(),
                booksPage.isFirst(),
                booksPage.isLast(),
                booksPage.hasNext(),
                booksPage.hasPrevious()
        );
    }

    public CreateBookResponse createBook(CreateBookRequest request) {
        Book newBook = bookMapper.toEntity(request);
        bookRepository.save(newBook);

        return bookMapper.toResponse(newBook);
    }
}

package com.tj330.bookservice.repository;

import com.tj330.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
}

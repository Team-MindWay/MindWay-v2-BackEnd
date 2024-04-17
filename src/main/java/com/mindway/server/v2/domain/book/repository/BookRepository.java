package com.mindway.server.v2.domain.book.repository;

import com.mindway.server.v2.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

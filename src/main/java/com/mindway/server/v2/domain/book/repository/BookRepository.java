package com.mindway.server.v2.domain.book.repository;

import com.mindway.server.v2.domain.book.entity.Book;
import com.mindway.server.v2.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByUser(User user);
}

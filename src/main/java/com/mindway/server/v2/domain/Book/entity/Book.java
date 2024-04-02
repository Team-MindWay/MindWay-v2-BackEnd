package com.mindway.server.v2.domain.Book.entity;

import com.mindway.server.v2.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String url;

    private LocalDate created_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

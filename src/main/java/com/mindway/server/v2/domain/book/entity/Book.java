package com.mindway.server.v2.domain.book.entity;

import com.mindway.server.v2.domain.user.entity.User;
import com.mindway.server.v2.global.entity.BaseEntity;
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
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String plot;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

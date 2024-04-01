package com.mindway.server.v2.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class User {

    @Id
    @GeneratedValue(generator = "UUID4")
    private UUID id;

    private String email;

    private String name;

    @Embedded
    private StudentNum studentNum;

    private String role;

}

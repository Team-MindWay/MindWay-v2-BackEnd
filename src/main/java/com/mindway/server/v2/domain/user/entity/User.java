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
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_name")
    private String name;

    @Embedded
    private StudentNum studentNum;

    @Column(name = "user_role")
    private String role;

}

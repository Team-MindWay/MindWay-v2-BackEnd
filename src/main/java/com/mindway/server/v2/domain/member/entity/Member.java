package com.mindway.server.v2.domain.member.entity;

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
public class Member {

    @Id
    @GeneratedValue(generator = "UUID4")
    @Column(name = "member_id")
    private UUID id;

    private String email;

    private String name;

    @Embedded
    private StudentNum studentNum;

    @Enumerated(EnumType.STRING)
    private Authority authority;

}

package com.mindway.server.v2.domain.goal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;

@Embeddable
@MappedSuperclass
@NoArgsConstructor
public class Week {

    @Column(length = 3)
    private Long mon;

    @Column(length = 3)
    private Long tue;

    @Column(length = 3)
    private Long wed;

    @Column(length = 3)
    private Long thu;

    @Column(length = 3)
    private Long fri;

    @Column(length = 3)
    private Long sat;

    @Column(length = 3)
    private Long sun;

}

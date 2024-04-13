package com.mindway.server.v2.domain.goal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Week {

    @Column(length = 3)
    private Integer mon = 0;

    @Column(length = 3)
    private Integer tue = 0;

    @Column(length = 3)
    private Integer wed = 0;

    @Column(length = 3)
    private Integer thu = 0;

    @Column(length = 3)
    private Integer fri = 0;

    @Column(length = 3)
    private Integer sat = 0;

    @Column(length = 3)
    private Integer sun = 0;

}

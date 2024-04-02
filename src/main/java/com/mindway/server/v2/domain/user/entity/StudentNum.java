package com.mindway.server.v2.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;

@Embeddable
@MappedSuperclass
@NoArgsConstructor
public class StudentNum {
    @Column(nullable = false, length = 1)
    private Integer grade;

    @Column(nullable = false, length = 1)
    private Integer classNum;

    @Column(nullable = false, length = 2)
    private Integer number;

    public StudentNum(Integer grade, Integer classNum, Integer number) {
        this.grade = grade;
        this.classNum = classNum;
        this.number = number;
    }

}

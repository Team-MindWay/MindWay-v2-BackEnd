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

    public void accrue(int day){
        switch (day) {
            case 1 -> mon++;
            case 2 -> tue++;
            case 3 -> wed++;
            case 4 -> thu++;
            case 5 -> fri++;
            case 6 -> sat++;
            case 7 -> sun++;
        }
    }

}

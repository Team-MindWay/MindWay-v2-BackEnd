package com.mindway.server.v2.domain.rank.entity;

import com.mindway.server.v2.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Ranks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer accrue = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void accrue() {
        accrue++;
    }

    public Ranks(User user) {
        this.user = user;
    }
}

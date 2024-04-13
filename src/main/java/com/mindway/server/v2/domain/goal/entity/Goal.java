package com.mindway.server.v2.domain.goal.entity;

import com.mindway.server.v2.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate started_at;

    private LocalDate ended_at;

    @Embedded
    private Week week_count;

    private Long goal_count;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

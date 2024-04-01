package com.mindway.server.v2.domain.event.entity;

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
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(name = "event_title")
    private String title;

    @Column(name = "event_content")
    private String content;

    @Column(name = "event_img_url")
    private String img_url;

    @Column(name = "event_started_at")
    private LocalDate started_at;

    @Column(name = "event_endedat")
    private LocalDate ended_at;

}

package com.mindway.server.v2.domain.rec.entity;

import com.mindway.server.v2.domain.rec.presentation.dto.reqest.UpdateRecRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "recommend")
public class Rec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String author;

    @Enumerated(EnumType.STRING)
    private Type type;

    public void updateRec (UpdateRecRequest updateRecRequest) {
        this.title = updateRecRequest.getTitle();;
        this.content = updateRecRequest.getContent();
        this.author = updateRecRequest.getAuthor();
    }
}

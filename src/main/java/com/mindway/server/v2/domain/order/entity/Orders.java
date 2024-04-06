package com.mindway.server.v2.domain.order.entity;

import com.mindway.server.v2.domain.order.presentation.dto.request.OrderUpdateRequest;
import com.mindway.server.v2.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String bookURL;

    private BookType bookType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void updateOrder (OrderUpdateRequest orderUpdate) {
        this.title = orderUpdate.getTitle();
        this.author = orderUpdate.getAuthor();
        this.bookURL = orderUpdate.getBook_url();
    }
}

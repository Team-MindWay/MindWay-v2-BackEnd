package com.mindway.server.v2.global.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    private ZonedDateTime create_at;

    @PrePersist
    public void prePersist() {
        this.create_at = ZonedDateTime.now();
    }
}

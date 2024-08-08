package com.wanted.wantedpreonboardingbackend.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class EntityBase {
    @Version
    protected Long version;

    @CreationTimestamp
    @Column(name = "createdAt")
    LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updatedAt")
    LocalDateTime updatedAt = LocalDateTime.now();
}

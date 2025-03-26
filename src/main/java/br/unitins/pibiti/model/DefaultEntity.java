package br.unitins.pibiti.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public abstract class DefaultEntity {

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PostPersist
    private void gerarCreatedAt() {

        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void gerarUpdateAt() {

        updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

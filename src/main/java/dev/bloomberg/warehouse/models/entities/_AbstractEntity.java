package dev.bloomberg.warehouse.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * Abstract base class for entities in the application.
 * Provides common fields such as id and timestamps.
 * Subclasses should use the @MappedSuperclass annotation.
 */
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class _AbstractEntity implements _Entity<Long> {
    /**
     * The unique identifier for the entity.
     */
    @Id
    @Column(unique = true)
    private Long id;

    /**
     * The timestamp indicating when the entity was created.
     */
    @CreationTimestamp
    @ReadOnlyProperty
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private Timestamp timestamp;
}
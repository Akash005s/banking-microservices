package com.easybank.app.loanservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Base entity for audit fields.
 * <p>
 * Automatically manages:
 * - createdOn
 * - createdBy
 * - updatedOn
 * - updatedBy
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    /**
     * Timestamp when record was created.
     */
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdOn;

    /**
     * User who created the record.
     */
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    /**
     * Timestamp when record was last updated.
     */
    @LastModifiedDate
    private LocalDateTime updatedOn;

    /**
     * User who last updated the record.
     */
    @LastModifiedBy
    private String updatedBy;
}


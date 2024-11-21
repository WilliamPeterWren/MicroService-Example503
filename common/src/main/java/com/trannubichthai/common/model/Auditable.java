package com.trannubichthai.common.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Auditable<U> {
    @CreatedBy
    protected U createdBy;

    @LastModifiedBy
    protected U lastModifiedBy;

    @CreatedDate
    protected LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedDate
    protected LocalDateTime updatedDate = LocalDateTime.now();
}
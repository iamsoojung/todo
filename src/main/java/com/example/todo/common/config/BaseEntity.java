package com.example.todo.common.config;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 모든 엔티티에 공통으로 들어가는 생성일/수정일 필드 관리
 */
@Getter
@MappedSuperclass       // 자식 엔티티에 매핑 정보만 제공 (테이블 생성 X)
@EntityListeners(AuditingEntityListener.class)      // JPA Auditing 기능과 연동
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
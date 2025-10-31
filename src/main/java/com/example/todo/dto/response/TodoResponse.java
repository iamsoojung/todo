package com.example.todo.dto.response;

import com.example.todo.domain.Todo;
import com.example.todo.domain.TodoStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TodoResponse {

    private Long id;
    private String userId;
    private String title;
    private String content;
    private TodoStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TodoResponse from(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .userId(todo.getUserId())
                .title(todo.getTitle())
                .content(todo.getContent())
                .status(todo.getStatus())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }
}

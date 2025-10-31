package com.example.todo.dto.request;

import com.example.todo.domain.TodoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoUpdateRequest {

    private String title;
    private String content;
    private TodoStatus status;
}

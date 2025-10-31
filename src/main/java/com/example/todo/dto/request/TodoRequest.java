package com.example.todo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequest {

    @NotBlank(message = "userId는 필수입니다.")
    private String userId;

    @NotBlank(message = "title은 필수입니다.")
    private String title;

    private String content;
}

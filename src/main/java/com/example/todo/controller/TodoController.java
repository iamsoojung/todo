package com.example.todo.controller;

import com.example.todo.common.response.ApiResponse;
import com.example.todo.dto.request.TodoRequest;
import com.example.todo.dto.request.TodoUpdateRequest;
import com.example.todo.dto.response.TodoResponse;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{id}")
    public ApiResponse<TodoResponse> get(@PathVariable Long id) {
        return ApiResponse.createSuccess(todoService.get(id), "조회 성공");
    }

    @GetMapping
    public ApiResponse<List<TodoResponse>> list(@RequestParam(required = false) String userId) {
        return ApiResponse.createSuccess(todoService.list(userId), "목록 조회 성공");
    }

    @PostMapping
    public ApiResponse<TodoResponse> create(@Valid @RequestBody TodoRequest todoRequest) {
        return ApiResponse.createSuccess(todoService.create(todoRequest), "등록 성공");
    }

    @PutMapping("/{id}")
    public ApiResponse<TodoResponse> update(@PathVariable Long id, @RequestBody TodoUpdateRequest todoUpdateRequest) {
        return ApiResponse.createSuccess(todoService.update(id, todoUpdateRequest), "수정 성공");
    }

    @DeleteMapping
    public ApiResponse<String> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ApiResponse.createSuccessWithNoData("삭제 성공");
    }
}

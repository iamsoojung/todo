package com.example.todo.controller;

import com.example.todo.common.response.ApiResponse;
import com.example.todo.dto.request.TodoRequest;
import com.example.todo.dto.request.TodoUpdateRequest;
import com.example.todo.dto.response.TodoResponse;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponse>> get(@PathVariable Long id) {
        TodoResponse response = todoService.get(id);
        return ResponseEntity.ok(ApiResponse.createSuccess(response, "조회 성공"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TodoResponse>>> list(@RequestParam(required = false) String userId) {
        List<TodoResponse> todos = todoService.list(userId);
        return ResponseEntity.ok(ApiResponse.createSuccess(todos, "목록 조회 성공"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TodoResponse>> create(@Valid @RequestBody TodoRequest todoRequest) {
        TodoResponse response = todoService.create(todoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccess(response, "등록 성공"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponse>> update(@PathVariable Long id,
                                                            @RequestBody TodoUpdateRequest todoUpdateRequest) {
        TodoResponse response = todoService.update(id, todoUpdateRequest);
        return ResponseEntity.ok(ApiResponse.createSuccess(response, "수정 성공"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.createSuccessWithNoData("삭제 성공"));
    }
}

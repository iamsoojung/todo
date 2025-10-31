package com.example.todo.service;

import com.example.todo.dto.request.TodoRequest;
import com.example.todo.dto.request.TodoUpdateRequest;
import com.example.todo.dto.response.TodoResponse;

import java.util.List;

public interface TodoService {

    // 단건 조회
    TodoResponse get(Long id);

    // 목록 조회
    List<TodoResponse> list(String userId);

    // 생성
    TodoResponse create(TodoRequest todoRequest);

    // 수정
    TodoResponse update(Long id, TodoUpdateRequest todoUpdateRequest);

    // 삭제
    void delete(Long id);
}
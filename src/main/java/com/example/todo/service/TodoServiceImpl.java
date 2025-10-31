package com.example.todo.service;

import com.example.todo.domain.Todo;
import com.example.todo.domain.TodoRepository;
import com.example.todo.domain.TodoStatus;
import com.example.todo.dto.request.TodoRequest;
import com.example.todo.dto.request.TodoUpdateRequest;
import com.example.todo.dto.response.TodoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    @Transactional(readOnly = true)
    public TodoResponse get(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));
        return TodoResponse.from(todo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoResponse> list(String userId) {
        List<Todo> todos = (userId != null && !userId.isEmpty())
                ? todoRepository.findByUserId(userId) : todoRepository.findAll();
        return todos.stream()
                .map(TodoResponse::from)
                .toList();
    }

    @Override
    public TodoResponse create(TodoRequest todoRequest) {
        Todo todo = Todo.builder()
                .userId(todoRequest.getUserId())
                .title(todoRequest.getTitle())
                .content(todoRequest.getContent())
                .status(TodoStatus.PLANNED)
                .build();
        return TodoResponse.from(todoRepository.save(todo));
    }

    @Override
    public TodoResponse update(Long id, TodoUpdateRequest todoUpdateRequest) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));

        todo.update(todoUpdateRequest.getTitle(), todoUpdateRequest.getContent(), todoUpdateRequest.getStatus());
        return TodoResponse.from(todo);
    }

    @Override
    public void delete(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new IllegalArgumentException("Todo not found");
        }
        todoRepository.deleteById(id);
    }
}

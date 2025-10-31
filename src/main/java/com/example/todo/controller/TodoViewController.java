package com.example.todo.controller;

import com.example.todo.dto.request.TodoRequest;
import com.example.todo.dto.request.TodoUpdateRequest;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoViewController {

    private final TodoService todoService;

    @GetMapping
    public String list(Model model, @RequestParam(required = false) String userId) {
        model.addAttribute("todos", todoService.list(userId));
        return "todo/board"; // 새로운 템플릿
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("todoRequest", new TodoRequest());
        return "todo/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute TodoRequest todoRequest) {
        todoService.create(todoRequest);
        return "redirect:/todos";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("todo", todoService.get(id));
        return "todo/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute TodoUpdateRequest todoUpdateRequest) {
        todoService.update(id, todoUpdateRequest);
        return "redirect:/todos";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        todoService.delete(id);
        return "redirect:/todos";
    }
}
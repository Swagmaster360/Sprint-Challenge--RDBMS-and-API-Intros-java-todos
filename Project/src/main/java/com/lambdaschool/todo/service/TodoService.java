package com.lambdaschool.todo.service;

import com.lambdaschool.todo.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();

    Todo findTodoById(long id);

    List<Todo> findByUserName (String username);

    void delete(long id);

    Todo save(Todo todo);

    Todo update(Todo todo, long id);

}

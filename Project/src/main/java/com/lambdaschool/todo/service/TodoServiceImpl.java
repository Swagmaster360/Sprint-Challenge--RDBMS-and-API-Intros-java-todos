package com.lambdaschool.todo.service;

import com.lambdaschool.todo.model.Todo;
import com.lambdaschool.todo.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "TodoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodosRepository todorepos;


    @Override
    public List<Todo> findAll() {
        List<Todo> list = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Todo findTodoById(long id) {
        return todorepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public List<Todo> findByUserName(String username) {
        return null;
    }

    @Override
    public void delete(long id) {

        if (todorepos.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (todorepos.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                todorepos.deleteById(id);
            } else
            {
                throw new EntityNotFoundException(Long.toString(id) + " " + authentication.getName());
            }
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }

    }

    @Override
    public Todo save(Todo todo) {
        return todorepos.save(todo);
    }

    @Override
    public Todo update(Todo todo, long id) {
        Todo newTodo = todorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

//        if (todo.getDescription() != null)
//        {
//            newTodo.setDescription(todo.getDescription());
//        }

            newTodo.setCompleted(todo.isCompleted());


        return todorepos.save(newTodo);
    }

}

package com.lambdaschool.todo.repository;

import com.lambdaschool.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodosRepository extends CrudRepository<Todo, Long> {


}

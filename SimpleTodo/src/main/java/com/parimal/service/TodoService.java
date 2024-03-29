package com.parimal.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.parimal.entity.Todo;

@Service
public interface TodoService
{
	List<Todo> getAllTodos();

	Todo createTodo(@Valid Todo todo);

	Todo updateTodo(Long id, @Valid Todo todo);

	Todo getTodoById(Long id);

	void deleteTodoById(Long id);

	Page<Todo> paginationandSorting(int page, int size, boolean sort);

	List<Todo> searchTodos(String searchTerm);

	List<Todo> getTodosByCompletion(boolean completed);
	
}

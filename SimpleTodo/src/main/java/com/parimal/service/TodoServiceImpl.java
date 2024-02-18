package com.parimal.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.parimal.entity.Todo;
import com.parimal.repository.TodoRepo;

@Service
public class TodoServiceImpl implements TodoService
{

	@Autowired
	private TodoRepo todoRepository;

	@Override
	public List<Todo> getAllTodos()
	{
		return todoRepository.findAll();
	}

	@Override
	public Todo createTodo(@Valid Todo todo)
	{
		return todoRepository.save(todo);
	}

	@Override
	public Todo updateTodo(Long id, @Valid Todo todo)
	{
		Todo existingTodo = todoRepository.findById(id).orElse(null);
		if (existingTodo == null)
		{
			return null; // or throw exception
		}
		existingTodo.setTitle(todo.getTitle());
		existingTodo.setCompleted(todo.isCompleted());
		return todoRepository.save(existingTodo);
	}

	@Override
	public Todo getTodoById(Long id)
	{
		return todoRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteTodoById(Long id)
	{
		todoRepository.deleteById(id);
	}

	@Override
	public Page<Todo> getAllTodos(int page, int size, String[] sort, String search)
	{
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort));
		if (search != null && !search.isEmpty())
		{
			return todoRepository.findByTitleContainingIgnoreCase(search, pageRequest);
		} else
		{
			return todoRepository.findAll(pageRequest);
		}
	}

}
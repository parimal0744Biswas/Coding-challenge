package com.parimal.service;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.parimal.entity.Todo;
import com.parimal.exception.TodoException;
import com.parimal.repository.TodoRepo;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TodoServiceImpl implements TodoService
{

	private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

	@Autowired
	private TodoRepo todoRepository;

	@Override
	public List<Todo> getAllTodos()
	{
		logger.info("Get all todos --> : {}", todoRepository.findAll());
		return todoRepository.findAll();
	}

	@Override
	public Todo createTodo(@Valid Todo todo)
	{
		logger.info("create Todo --> : {}", todo);

		return todoRepository.save(todo);
	}

	@Override
	public Todo updateTodo(Long id, @Valid Todo todo)
	{
		logger.info("update Todo with id --> : {} - {}", todo, id);
		Todo existingTodo = todoRepository.findById(id)
				.orElseThrow(() -> new TodoException("Todo not found with this id : " + id));

		existingTodo.setTitle(todo.getTitle());
		existingTodo.setCompleted(todo.isCompleted());

		return todoRepository.save(existingTodo);
	}

	@Override
	public Todo getTodoById(Long id)
	{
		logger.info("Todo id --> : {}", id);
		return todoRepository.findById(id).orElseThrow(() -> new TodoException("Todo not found with this id : " + id));

	}

	@Override
	public void deleteTodoById(Long id)
	{
		todoRepository.findById(id).orElseThrow(() -> new TodoException("Todo not found with this id : " + id));
		todoRepository.deleteById(id);
	}

	@Override
	public Page<Todo> paginationandSorting(int page, int size, boolean sort) // String search
	{
		Pageable pageable;
		if (sort == true)
		{
			pageable = PageRequest.of(page, size, Sort.by("title").descending());
		} else
		{
			pageable = PageRequest.of(page, size, Sort.by("title").ascending());
		}

		return todoRepository.findAll(pageable);

		// return new PageImpl<>(null);
	}

}

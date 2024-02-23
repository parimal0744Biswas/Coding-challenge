package com.parimal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parimal.entity.Todo;
import com.parimal.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController
{
	@Autowired
	private TodoService todoService;

	@GetMapping
	public ResponseEntity<List<Todo>> getAllTodos()
	{
		return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo)
	{
		Todo createdTodo = todoService.createTodo(todo);
		return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todo)
	{
		Todo updatedTodo = todoService.updateTodo(id, todo);
		return ResponseEntity.ok(updatedTodo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable Long id)
	{
		Todo todo = todoService.getTodoById(id);
		return ResponseEntity.ok(todo);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTodoById(@PathVariable Long id)
	{
		todoService.deleteTodoById(id);
		return new ResponseEntity<>("todo deleted !", HttpStatus.OK);
	}

	@GetMapping("/paging")
	public Page<Todo> getPaginatedAndSortedTodos(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "false") boolean sort)
	{
		return todoService.paginationandSorting(page, size, sort);
	}

	@GetMapping("/search")
	public List<Todo> searchTodos(@RequestParam(required = false) String searchTerm)
	{
		return todoService.searchTodos(searchTerm);
	}

	@GetMapping("/completed")
	public List<Todo> getCompletedTodos(@RequestParam(defaultValue = "true") boolean completed)
	{
		return todoService.getTodosByCompletion(completed);
	}

}

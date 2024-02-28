package com.parimal.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parimal.entity.Todo;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long>
{
	Page<Todo> findByTitleContainingIgnoreCase(String title, Pageable pageable);

	Page<Todo> findAll(Pageable pageable);

	List<Todo> findByTitleContainingIgnoreCase(String searchTerm);

	List<Todo> findByCompleted(boolean done);
	
}

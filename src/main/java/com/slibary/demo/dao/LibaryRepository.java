package com.slibary.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slibary.demo.libary.entity.Author;

public interface LibaryRepository extends JpaRepository<Author, String> {

	public Optional<Author> findByUsernameAndPassword(String username, String password);
	
	public Optional<Author> findByUsername(String username);

}

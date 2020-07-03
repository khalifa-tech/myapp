package com.slibary.demo.service;

import java.util.List;

import com.slibary.demo.libary.entity.Author;


public interface LibaryserviceDao {
	
	
    public List<Author> findallauthors();
	
	//public List<Book> findallbooks();
	
	public Author findbyId(String theId);
	
	
	public Author findbyusername(String username);
	
	public void saveAuthor(Author author);
	
	//public void saveBook(Book book);
	
	public void deleteAuthor(String theid);
	
	
	public Author loginDetails(String username, String password);

	
	//public void deleteBook(String theid);
}

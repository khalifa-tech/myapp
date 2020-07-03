package com.slibary.demo.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.slibary.demo.dao.LibaryRepository;
import com.slibary.demo.dao.LibarybookRepo;
import com.slibary.demo.libary.entity.Author;
import com.slibary.demo.libary.entity.Book;

@Service
public class Libaryserviceimpo implements LibaryserviceDao  {
	
	private LibaryRepository libaryRepository;

	
	@Autowired
	public Libaryserviceimpo(LibaryRepository thelibaryRepository ) {
		libaryRepository = thelibaryRepository;

	}

	@Override
	public List<Author> findallauthors() {
		// TODO Auto-generated method stub
		return libaryRepository.findAll();
	}

	@Override
	public Author findbyId(String theId) {
		// TODO Auto-generated method stub
		Optional<Author> result = libaryRepository.findById(theId);
		
		Author author = null;
		
		if(result.isPresent()) {
			author = result.get();
		}
		else {
			throw new RuntimeException("Cannot find any author with the id provided");
		}
		return author;
	}

	@Override
	public void saveAuthor(Author author) {
		
		libaryRepository.save(author);
		
	}

	@Override
	public void deleteAuthor(String theid) {
		
		libaryRepository.deleteById(theid);
		
	}

	@Override
	public Author loginDetails(String username, String password) {
		
		 Optional<Author> result = libaryRepository.findByUsernameAndPassword(username, password);
		 
		 Author author = null;
		 
		 if(result.isPresent()) {
			 author = result.get();
		 }
		 else {
			 throw new RuntimeException("Username and password doesnot match");
		 }
		 
		return author;
	}

	@Override
	public Author findbyusername(String username) {
		
		Optional<Author> result = libaryRepository.findByUsername(username);
		
		Author author = null;
		
		if(result.isPresent()) {
			author = result.get();
		}
		else {
			throw new RuntimeException("Username does not exist");
		}
		
		return author;
	}
}

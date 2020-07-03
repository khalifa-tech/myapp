package com.slibary.demo.dao;


import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.slibary.demo.libary.entity.Author;
import com.slibary.demo.libary.entity.Book;
import java.util.List;


public interface LibarybookRepo extends JpaRepository<Book, String> {
	
	@Query(value="SELECT * FROM book b WHERE b.auth_id=:author", nativeQuery = true)
	List<Book> findAllBooksByAuthor(String author);
	
	@Query(value="SELECT * FROM book where bookname=:bookname ", nativeQuery = true)
	List<Book> findfindByBookname(String bookname);
}

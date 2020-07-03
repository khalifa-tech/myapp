package com.slibary.demo.restController;

import java.util.ArrayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.slibary.demo.dao.LibarybookRepo;
import com.slibary.demo.libary.entity.Author;
import com.slibary.demo.libary.entity.Book;
import com.slibary.demo.service.LibaryserviceDao;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Bookcontroller {
	
	@Autowired
	private LibarybookRepo libarybookRepo;
	
	@Autowired
	private LibaryserviceDao libaryservicedao;
	
	public Bookcontroller(LibaryserviceDao thelibary) {
		
		libaryservicedao = thelibary;
	}
	
	
	@GetMapping("/bookslist")
	public 	List<Book> getAllBooks(){
		
		
		return libarybookRepo.findAll();
	}
		
	    @PostMapping("/books/{authorId}/book")
	    public Book newBook(@PathVariable String authorId, @RequestBody Book book
	    		 ){
	    	
	    	int random = (int)(Math.random() * 100000 + 100000);
	    	
	    	Author author = libaryservicedao.findbyId(authorId);
	    	
	    	if(author == null) {
				throw new RuntimeException("Author id not found - " + authorId);
			}
	    
			book.setAuthor(author);
			book.setIsbn("ISBN"+random);
			
			libarybookRepo.save(book);
			
			return book;
	    }
	    
	    
	    @GetMapping("/book/{bookname}")
	    public List<Book> bookName(@PathVariable String bookname) {
	    	
	    	List<Book> items = new ArrayList<>();
	    	
	        libarybookRepo.findfindByBookname(bookname).forEach(items::add);
	        
	        return items;
	    	
	    }

	   
	    @GetMapping("/books/{bookid}/book")
	    public Book getOneBook(@PathVariable String bookid) {
	    	
	    	Optional<Book> result = libarybookRepo.findById(bookid);
	    	
	    	Book book = null;
	    	
	    	if(result.isPresent()) {
	    		book = result.get();
	    	}
	    	else {
	    		throw new RuntimeException("Cannot find any book with the id provided");
	    	}
	    	
	    	return book;
	    	
	    }
	    
	    @GetMapping("/bookslist/{authorid}")
	    public List<Book> getallBooksbyAuthor(@PathVariable("authorid") String authorid){
	    	List<Book> items = new ArrayList<>();
	    	
	    	libarybookRepo.findAllBooksByAuthor(authorid).forEach(items::add);
	    	return items;
	    }
	    
	    @PutMapping("/books/{authorId}/book/{bookId}")
	    public Book updateBook(@PathVariable String authorId, @PathVariable String bookId,
	    		@RequestBody Book book){
	    	
	    	
	    	Author author = libaryservicedao.findbyId(authorId);
	    	
	    	if(author == null) {
				throw new RuntimeException("Author id not found - " + authorId);
			}
	    	
            return libarybookRepo.findById(bookId).map(books ->{
            	books.setBookname(book.getBookname());
            	books.setBookimg(book.getBookimg());
            	books.setBookprice(book.getBookprice());
            	
            	return libarybookRepo.save(books);
            }).orElseThrow(() ->  new RuntimeException("Cannot find any book with the id provided") );
	    	
	    }

	  @DeleteMapping("/book/{bookid}/delete")
	  public String deleteBook(@PathVariable String bookid) {
		  
		  Optional<Book> result = libarybookRepo.findById(bookid);
		  
		  if(!result.isPresent()) {
			  throw new RuntimeException("Cannot delete a book with an invalid book ID");
		  }
		  
		  libarybookRepo.deleteById(bookid);
		  
		  return bookid;
	  }

}

package com.slibary.demo.restController;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slibary.demo.dao.LibaryRepository;
import com.slibary.demo.libary.entity.Author;
import com.slibary.demo.libary.entity.Login;
import com.slibary.demo.service.LibaryserviceDao;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Authorcontroller {
	
	@Autowired
	private LibaryRepository libaryRepo;
	
	private LibaryserviceDao libaryservicedao;
	
	public Authorcontroller(LibaryserviceDao thelibary) {
		
		libaryservicedao = thelibary;
	}
	
	@GetMapping("/authors")
	public List<Author> findall(){
		
		return libaryservicedao.findallauthors();
	}
	
	
	@GetMapping("/authors/{authorid}")
	public Author getAuthor(@PathVariable String authorid) {
		
		Author theauthor = libaryservicedao.findbyId(authorid);
		
		if(theauthor == null) {
			throw new RuntimeException("Author id not found - " + authorid);
		}
		
		return theauthor;
	}
	
	@GetMapping("/author/{username}")
	public Author checkUsername(@PathVariable String username) {
		
		Author author = libaryservicedao.findbyusername(username);
		
		if(author == null) {
			throw new RuntimeException("username does not exist");
		}
		
		return author;
	}
	
	@PostMapping("/author/login")
	public Author signin(@RequestBody Login login) {
		
		Author author = libaryservicedao.loginDetails(login.getUsername(), login.getPassword());
		
		if(author == null) {
			throw new RuntimeException("invalid credentials");
		}
		
		return author;
	}
	
	
	@PostMapping("/newauthor")
	public Author addAuthor(@RequestBody Author theauthor) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date convertedCurrentDate = null;
		try {
			convertedCurrentDate = sdf.parse(theauthor.getDob());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String date=sdf.format(convertedCurrentDate );
		theauthor.setDob(date);
		libaryservicedao.saveAuthor(theauthor);
		
		return theauthor;
	}
	
	@CrossOrigin("*")
	@PutMapping("/updateauthor")
	public Author updateAuto(@RequestBody Author theauthor) {
		
		libaryservicedao.saveAuthor(theauthor);
		
		return theauthor;
	}
	
	@DeleteMapping("/author/{authorid}")
	public String deleteAuthor(@PathVariable String authorid) {
		
       libaryRepo.deleteById(authorid);
		
		return "Deleted author id - " + authorid;
	}
	

}

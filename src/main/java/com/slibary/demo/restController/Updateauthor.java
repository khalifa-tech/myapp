package com.slibary.demo.restController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slibary.demo.libary.entity.Author;
import com.slibary.demo.service.LibaryserviceDao;

@RestController
public class Updateauthor {
	
    private LibaryserviceDao libaryservicedao;
	
	public  Updateauthor(LibaryserviceDao thelibary) {
		
		libaryservicedao = thelibary;
	}
	
	@CrossOrigin("*")
	@PutMapping("/authors/{authid}")
	public Author updateAuto(@PathVariable String authid, @RequestBody Author theauthor) {
		
		libaryservicedao.saveAuthor(theauthor);
		
		return theauthor;
	}
	

}

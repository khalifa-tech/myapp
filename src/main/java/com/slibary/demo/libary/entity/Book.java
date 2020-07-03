package com.slibary.demo.libary.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.slibary.demo.libary.entity.Author;
import com.slibary.demo.utils.IdGenerator;

@Entity
@Table(name="book")
public class Book implements Serializable {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @GenericGenerator(
        name = "book_seq", 
        strategy = "com.slibary.demo.utils.IdGenerator", 
        parameters = {
        		@org.hibernate.annotations.Parameter(name = IdGenerator.INCREMENT_PARAM, value = "01"),
        		@org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "book"),
        		@org.hibernate.annotations.Parameter(name = IdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
    @Column(name="bookid")
	private String bookid;
	
	 @ManyToOne
	 @JoinColumn(name="auth_id")
	 @JsonIgnore
	 private Author author;
	
	@Column(name="bookname")
	private String bookname;
	
	
	@Column(name="isbn")
	private String isbn;
	
	@Column(name="bookprice")
	private String bookprice;
	
	@Column(name="bookimg", length= 100000)
	private String bookimg;
	

	public String getBookprice() {
		return bookprice;
	}

	public void setBookprice(String bookprice) {
		this.bookprice = bookprice;
	}

	
	public String getBookimg() {
		return bookimg;
	}

	public void setBookimg(String bookimg) {
		this.bookimg = bookimg;
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Book [bookid=" + bookid + ", author=" + author + ", bookname=" + bookname + ", isbn=" + isbn
				+ ", bookprice=" + bookprice + ", bookimg=" + bookimg + "]";
	}

	

	

}

package com.slibary.demo.libary.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import com.slibary.demo.libary.entity.Book;
import com.slibary.demo.utils.IdGenerator;

@Entity
@Table(name="Author")
public class Author implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq")
    @GenericGenerator(
        name = "author_seq", 
        strategy = "com.slibary.demo.utils.IdGenerator", 
        parameters = {
        		@org.hibernate.annotations.Parameter(name = IdGenerator.INCREMENT_PARAM, value = "01"),
        		@org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "auth"),
        		@org.hibernate.annotations.Parameter(name = IdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
	@Column(name="authid")
	private String authid;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="dob")
	private String dob;
	
	@Column(name="username", unique=true)
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="authorImg", length= 100000)
	private String authorImg;
	
	@OneToMany(mappedBy="author",fetch = FetchType.LAZY,
       cascade={ CascadeType.REMOVE}
	        )
    private List<Book> books; 
	

//    @ElementCollection 
//    @JoinTable(name="authorities", joinColumns=
//     {@JoinColumn(name="authid",referencedColumnName="authid"),
//    		 @JoinColumn(name="username",referencedColumnName="username")})
//    private Collection<Authorithy> authority = new HashSet<>();

	
	public Author() {
		
	}


	public String getAuthid() {
		return authid;
	}

	public void setAuthid(String authid) {
		this.authid = authid;
	}

	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	

	public String getAuthorImg() {
		return authorImg;
	}


	public void setAuthorImg(String authorImg) {
		this.authorImg = authorImg;
	}


	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
//
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
//
//	public Collection<Authorithy> getAuthority() {
//		return authority;
//	}
//
//	public void setAuthority(Collection<Authorithy> authority) {
//		this.authority = authority;
//	}


	@Override
	public String toString() {
		return "Author [authid=" + authid + ", firstname=" + firstname + ", lastname=" + lastname + ", dob=" + dob
				+ ", username=" + username + ", password=" + password + ", authorImg=" + authorImg + ", books=" + books
				+ "]";
	}


	
		
}

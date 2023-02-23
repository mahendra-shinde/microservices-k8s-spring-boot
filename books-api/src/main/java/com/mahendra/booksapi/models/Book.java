package com.mahendra.booksapi.models;

import java.io.Serializable;
import javax.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name="books")
@Schema(name = "Book", description="Book object represents Individual Books in library")
public class Book implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id",scale = 5, precision = 0)
	@Schema(name="id", description = "Book Id" , type = "Integer" , example="101")
	private Integer id;

	@Column(name = "title", length = 50)
	@Schema(description="Book Title", name="title", type="String", example="Let Us C")
	private String title;

	@Column(name="book_author",length = 50)
	@Schema(description = "Name of Author", name="author", type="String", example="Yashwant Kanetkar")
	private String author;

	@Schema(description = "Book Category", name="category", type="String", example="Programming")
	@Column(name="book_category",length = 40)
	private String category;

	@Schema(description = "Status of Book ('A' => Available, 'N' => Not Available )", name="status", type="String", example="A")
	@Column(name="book_status",length = 1)
	private char status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Book(String title, String author, String category, char status) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
	}
	public Book() {
		super();
	}
}

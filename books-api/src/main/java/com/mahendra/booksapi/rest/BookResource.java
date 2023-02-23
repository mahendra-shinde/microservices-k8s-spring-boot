package com.mahendra.booksapi.rest;

import com.mahendra.booksapi.dao.BookDAO;
import com.mahendra.booksapi.exceptions.BookNotFoundException;
import com.mahendra.booksapi.models.Book;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/books")
@Tag(name="/api/books", description="Operations for Books")
public class BookResource {

	@Autowired private BookDAO dao;
	
	@GetMapping(produces = "application/json")
	@Operation(description = "List all books from books-api")
	public ResponseEntity<List<Book>> findAll(){
		List<Book> books = new LinkedList<>();
		dao.findAll().forEach(b -> books.add(b));
		if(books.isEmpty()){
			throw new BookNotFoundException();
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	@GetMapping(produces = "application/json",value = "/{ID}")
	@Operation(description = "Find book by Book ID")
	public ResponseEntity<Book> findBook(@PathVariable("ID") Integer id){
		Optional<Book> book = dao.findById(id);
		if(book.isEmpty()){
			throw new BookNotFoundException();
		}
		return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
	}

	@PostMapping(produces="application/json", consumes="application/json")
	@Operation(description =  "Save new book")
	public ResponseEntity<Book> save(Book book){
		Book newBook = dao.save(book);
		return new ResponseEntity<>(newBook, HttpStatus.OK);
	}

	@PutMapping(produces = "application/json", consumes="application/json", value = "/{ID}")
	public ResponseEntity<Book> update(@PathVariable("ID") int bookId, Book book){
		Optional<Book> oldBook = dao.findById(bookId);
		if(oldBook.isEmpty()){
			throw new BookNotFoundException();
		}
		Book newBook = dao.save(book);
		return new ResponseEntity<>(newBook, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{ID}", produces = "application/json")
	@Operation(description = "Delete a book by id")
	public ResponseEntity<String> delete(@PathVariable("ID") int bookId){
		dao.deleteById(bookId);
		return new ResponseEntity<>("Successfully deleted a book",HttpStatus.OK);
	}
}

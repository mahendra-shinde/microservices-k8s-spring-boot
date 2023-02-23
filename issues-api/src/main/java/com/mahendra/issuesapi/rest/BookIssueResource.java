package com.mahendra.issuesapi.rest;

import com.mahendra.issuesapi.dao.BookIssueDAO;
import com.mahendra.issuesapi.exceptions.IssueNotFoundException;
import com.mahendra.issuesapi.models.BookIssue;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/issues")
@Tag(name = "/api/issues", description = "Operations for Book-Issues")
public class BookIssueResource {

	@Autowired
	private BookIssueDAO dao;

	@GetMapping(produces = "application/json")
	@Operation(description="Get all the Book Issues")
	public ResponseEntity<List<BookIssue>> findAll() {
		List<BookIssue> issues = new LinkedList<>();
		dao.findAll().forEach(issues::add);
		if (issues.isEmpty()) {
			throw new IssueNotFoundException();
		}
		return new ResponseEntity<>(issues, HttpStatus.FOUND);
	}

	@GetMapping(produces = "application/json", value = "/{ID}")
	@Operation(description="Search Issue by Issue ID")
	public ResponseEntity<BookIssue> findIssue(@PathVariable("ID") int issueId) {
		Optional<BookIssue> issue = dao.findById(issueId);
		if (issue.isEmpty()) {
			throw new IssueNotFoundException();
		}
		return new ResponseEntity<>(issue.get(), HttpStatus.FOUND);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	@Operation(description="Issue a book to member")
	public ResponseEntity<BookIssue> save(BookIssue issue) {
		BookIssue newIssue = dao.save(issue);
		return new ResponseEntity<>(newIssue, HttpStatus.CREATED);
	}

	@PutMapping(value = "{ID}", produces = "application/json", consumes = "application/json")
	@Operation(description="Updates an issue using Issue ID")
	public ResponseEntity<BookIssue> update(@PathVariable("ID") int issueId, BookIssue issue) {
		Optional<BookIssue> oldIssue = dao.findById(issueId);
		if (oldIssue.isEmpty()) {
			throw new IssueNotFoundException();
		}
		issue.setId(issueId);
		BookIssue newIssue = dao.save(issue);
		return new ResponseEntity<>(newIssue, HttpStatus.OK);
	}

	@DeleteMapping(value = "{ID}", produces = "application/json")
	@Operation(description="Delete an Issue by Issue ID")
	public ResponseEntity<String> delete(@PathVariable("ID") int issueId) {
		dao.deleteById(issueId);
		return new ResponseEntity<>("Member successfully deleted !", HttpStatus.OK);
	}
}

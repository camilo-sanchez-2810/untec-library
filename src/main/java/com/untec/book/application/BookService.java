package com.untec.book.application;

import java.util.List;

import com.untec.book.domain.Book;
import com.untec.book.domain.BookRepository;

public class BookService {
	private BookRepository repository;
	
	public BookService(BookRepository repository) {
		this.repository = repository;
	}
	
	public int getTotalPages(int pageSize) {
		long totalRecord = repository.getTotalRecord();
		return (int) Math.ceil((double) totalRecord / pageSize);
	}
	
	public List<Book> getBooks(int pageSize, int pageNumber) {
		int offset = (pageNumber - 1) * pageSize;
		return repository.findAll(pageSize, offset);
	}
}

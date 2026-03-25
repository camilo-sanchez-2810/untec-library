package com.untec.books.domain;

import java.util.List;

public interface BookRepository {
	public List<Book> findAll(int pageNumber, int pageSize);
	public long getTotalRecord();
}

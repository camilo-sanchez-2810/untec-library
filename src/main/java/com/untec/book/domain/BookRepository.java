package com.untec.book.domain;

import java.util.List;

public interface BookRepository {
	public List<Book> findAll(int pageSize, int offset);
	public long getTotalRecord();
}

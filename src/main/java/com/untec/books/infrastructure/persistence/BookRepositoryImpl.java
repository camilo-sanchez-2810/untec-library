package com.untec.books.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.untec.books.domain.Book;
import com.untec.books.domain.BookRepository;
import com.untec.books.domain.FileType;
import com.untec.shared.infrastructure.db.Database;

import static com.untec.shared.utils.UUUIDUtils.fromBytes;

public class BookRepositoryImpl implements BookRepository {
	private Database database;
	
	public BookRepositoryImpl(Database database) {
		this.database = database;
	}
	
	@Override
	public List<Book> findAll(int pageNumber, int pageSize) {
		Connection connection = database.getConnection();
		List<Book> books = new ArrayList<Book>();
		try {			
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM book LIMIT ? OFFSET ?");
			statement.setInt(1,pageSize);
			statement.setInt(2,  pageSize * (pageNumber -1));
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Book book = new Book(
						fromBytes(resultSet.getBytes("id")),
						resultSet.getString("title"),
						resultSet.getString("cover_url"),
						resultSet.getString("description"),
						FileType.fromString(resultSet.getString("file_type")),
						resultSet.getString("file_url"),
						resultSet.getInt("languague"),
						resultSet.getInt("year"));
				books.add(book);
			}
			
			return books;
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener los libros", e);
		}
	}
	
	
	@Override
	public long getTotalRecord() {
		Connection connection = database.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS total FROM book");
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getLong("total");
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la cantidad de libros", e);
		}
	}
}

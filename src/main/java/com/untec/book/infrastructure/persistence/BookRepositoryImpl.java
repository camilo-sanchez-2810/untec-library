package com.untec.book.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.untec.book.domain.Book;
import com.untec.book.domain.BookRepository;
import com.untec.book.domain.FileType;
import com.untec.shared.infrastructure.db.Database;

import static com.untec.shared.utils.UUUIDUtils.fromBytes;

public class BookRepositoryImpl implements BookRepository {
	private Database database;
	
	public BookRepositoryImpl(Database database) {
		this.database = database;
	}
	
	@Override
	public List<Book> findAll(int pageSize, int offset) {
		Connection connection = database.getConnection();
		List<Book> books = new ArrayList<Book>();
		try {			
			PreparedStatement statement = connection.prepareStatement("SELECT b.id, b.title, b.cover_url, b.description, b.file_type, b.file_url, l.name AS language, b.year, GROUP_CONCAT(DISTINCT CONCAT(a.name, ' ', a.surname) SEPARATOR ', ') AS authors, GROUP_CONCAT(DISTINCT c.title SEPARATOR ', ') AS categories FROM book b LEFT JOIN language l ON b.language_id = l.id LEFT JOIN book_author ba ON b.id = ba.book_id LEFT JOIN author a ON ba.author_id = a.id LEFT JOIN book_category bc ON b.id = bc.book_id LEFT JOIN category c ON bc.category_id = c.id GROUP BY b.id LIMIT ? OFFSET ?;");
			statement.setInt(1,pageSize);
			statement.setInt(2,  offset);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				String authors = resultSet.getString("authors");
				if (authors == null) authors = "Sin autores";

				String categories = resultSet.getString("categories");
				if (categories == null) categories = "General";
				
				Book book = new Book(
						fromBytes(resultSet.getBytes("id")),
						resultSet.getString("title"),
						resultSet.getString("cover_url"),
						resultSet.getString("description"),
						FileType.fromString(resultSet.getString("file_type")),
						resultSet.getString("file_url"),
						resultSet.getString("language"),
						authors,
						categories,
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

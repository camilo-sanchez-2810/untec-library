package com.untec.user.infrastructure.persistence;

import static com.untec.shared.utils.UUUIDUtils.toBytes;
import com.untec.user.domain.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.untec.shared.infrastructure.db.Database;
import com.untec.user.domain.Librarian;
import com.untec.user.domain.Student;
import com.untec.user.domain.User;

public class UserRepositoryImpl implements UserRepository {
	private Database database;
	
	public UserRepositoryImpl(Database database) {
		this.database = database;
	}
	
	@Override
    public void createUser(User user) {
		Connection connection = database.getConnection();
		
		try {
			connection.setAutoCommit(false);
			
			PreparedStatement userStatement = connection.prepareStatement("INSERT INTO user(id, name, middle_name, surname, second_surname, email, password, type) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			userStatement.setBytes(1,  toBytes(user.getId()));
			userStatement.setString(2, user.getName());
			userStatement.setString(3, user.getMiddleName());
			userStatement.setString(4, user.getSurname());
			userStatement.setString(5, user.getSecondSurname());
			userStatement.setString(6, user.getEmail());
			userStatement.setString(7, user.getPassword());
			userStatement.setString(8, user.getType().getType());
			userStatement.executeUpdate();
			
			if (user instanceof Student student) {
				PreparedStatement studentStatement = connection.prepareStatement("INSERT INTO student(id, user_id, max_loan_limit) VALUES (?, ?, ?)");
				studentStatement.setBytes(1, toBytes(student.getStudentId()));
				studentStatement.setBytes(2, toBytes(student.getId()));
				studentStatement.setInt(3, student.getMaxLoan());
				studentStatement.executeUpdate();
			} else if (user instanceof Librarian librarian) {
				PreparedStatement librarianStatement = connection.prepareStatement("INSERT INTO librarian(id, user_id, access_level) VALUES (?, ?, ?)");
				librarianStatement.setBytes(1, toBytes(librarian.getLibrarianId()));
				librarianStatement.setBytes(2, toBytes(librarian.getId()));
				librarianStatement.setInt(3, librarian.getAccessLevel());
				librarianStatement.executeUpdate();
			}
			
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			throw new RuntimeException("Error al guardar el usuario", e);
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
}

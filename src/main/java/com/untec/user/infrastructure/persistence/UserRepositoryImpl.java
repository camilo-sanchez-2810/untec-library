package com.untec.user.infrastructure.persistence;

import static com.untec.shared.utils.UUUIDUtils.toBytes;
import static com.untec.shared.utils.UUUIDUtils.fromBytes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import com.untec.shared.infrastructure.db.Database;
import com.untec.user.domain.Librarian;
import com.untec.user.domain.Status;
import com.untec.user.domain.Student;
import com.untec.user.domain.User;
import com.untec.user.domain.UserRepository;
import com.untec.user.domain.UserType;

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

	@Override
	public Optional<User> findByEmail(String email) {
		Connection connection = database.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(
				"SELECT id, name, middle_name, surname, second_surname, email, password, type, created_at " +
				"FROM user WHERE email = ?"
			);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.next()) return Optional.empty();

			UUID userId = fromBytes(resultSet.getBytes("id"));
			String name = resultSet.getString("name");
			String middleName = resultSet.getString("middle_name");
			String surname = resultSet.getString("surname");
			String secondSurname = resultSet.getString("second_surname");
			String userEmail = resultSet.getString("email");
			String password = resultSet.getString("password");
			UserType type = UserType.fromType(resultSet.getString("type"));
			Timestamp createdAt = resultSet.getTimestamp("created_at");

			User user = switch (type) {
				case STUDENT -> {
					PreparedStatement s = connection.prepareStatement(
						"SELECT id, status, max_loan_limit FROM student WHERE user_id = ?"
					);
					s.setBytes(1, toBytes(userId));
					ResultSet sr = s.executeQuery();
					sr.next();
					yield new Student(
						userId,
						fromBytes(sr.getBytes("id")),
						name, middleName, surname, secondSurname, userEmail, password,
						Status.fromStatus(sr.getString("status")),
						createdAt.toLocalDateTime()
					);
				}
				case LIBRARIAN -> {
					PreparedStatement s = connection.prepareStatement(
						"SELECT id, access_level FROM librarian WHERE user_id = ?"
					);
					s.setBytes(1, toBytes(userId));
					ResultSet lr = s.executeQuery();
					lr.next();
					yield new Librarian(
						userId,
						fromBytes(lr.getBytes("id")),
						name, middleName, surname, secondSurname, userEmail, password,
						lr.getInt("access_level"),
						createdAt.toLocalDateTime()
					);
				}
			};

			return Optional.of(user);
		} catch (SQLException e) {
			throw new RuntimeException("Error al buscar usuario", e);
		}
	}
}

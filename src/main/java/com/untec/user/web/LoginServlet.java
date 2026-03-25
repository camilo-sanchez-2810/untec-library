package com.untec.user.web;

import java.io.IOException;
import java.sql.SQLException;

import com.untec.shared.infrastructure.db.Database;
import com.untec.shared.infrastructure.db.MySQLDatabase;
import com.untec.user.application.UserService;
import com.untec.user.application.dto.FindUserByEmailDTO;
import com.untec.user.domain.Student;
import com.untec.user.domain.User;
import com.untec.user.domain.UserRepository;
import com.untec.user.domain.exception.UserInactiveException;
import com.untec.user.infrastructure.persistence.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private UserRepository repository;
	private UserService service;
	
	public void init() throws ServletException {
		try {
			Database database = MySQLDatabase.getInstance();
			this.repository = new UserRepositoryImpl(database);
			this.service = new UserService(null, repository);
		} catch (SQLException | IOException e) {
			throw new ServletException("Error al inicializar el servlet", e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		FindUserByEmailDTO dto = new FindUserByEmailDTO(email, password);
		
		try {
			User user = service.findUserByEmail(dto);
			HttpSession session = request.getSession();
			session.setAttribute("name", user.getName());
			session.setAttribute("middleName", user.getMiddleName());
			session.setAttribute("surname", user.getSurname());
			session.setAttribute("secondSurname", user.getSecondSurname());
			session.setAttribute("type", user.getType().getType());
			session.setAttribute("id", user.getId().toString());
			if (user instanceof Student student) {
				session.setAttribute("maxLoan", student.getMaxLoan());
				session.setAttribute("studentId", student.getStudentId().toString());
				request.getRequestDispatcher("/student/menu.jsp").forward(request, response);
			}
		} catch (IllegalArgumentException e) {
			String msg = e.getMessage();
			if (msg != null && msg.startsWith("Correo")) request.setAttribute("errorEmail", msg);
			else if (msg != null && msg.startsWith("Contraseña")) request.setAttribute("errorPassword", msg);
			else request.setAttribute("error", msg);
			request.setAttribute("formValue", dto);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}catch (UserInactiveException e) {
			request.setAttribute("error", e.getMessage());
			request.setAttribute("formValue", dto);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} catch (RuntimeException e) {
			request.setAttribute("error", e.getMessage());
			request.setAttribute("formValue", dto);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}

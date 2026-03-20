package com.untec.user.web;

import java.io.IOException;
import java.sql.SQLException;

import com.untec.shared.infrastructure.db.Database;
import com.untec.shared.infrastructure.db.MySQLDatabase;
import com.untec.user.application.UserService;
import com.untec.user.application.dto.CreateUserDTO;
import com.untec.user.domain.UserFactory;
import com.untec.user.domain.UserRepository;
import com.untec.user.infrastructure.persistence.UserRepositoryImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/create-user")
public class CreateUserServlet extends HttpServlet { 
	private UserRepository repository;
	private UserFactory factory;
	private UserService service;
	
	@Override
	public void init() throws ServletException {
		try {
			Database database = MySQLDatabase.getInstance();
			this.repository = new UserRepositoryImpl(database);
			this.factory = new UserFactory();
			this.service = new UserService(factory, repository);
		} catch (SQLException | IOException e) {
			throw new ServletException("Error al inicializar la conexión", e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		request.getRequestDispatcher("/create-user.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String middleName = request.getParameter("middle_name");
		String surname = request.getParameter("surname");
		String secondSurnameString = request.getParameter("second_surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		Integer accessLevel = null;
		if (request.getParameter("access_level") != null) {
			accessLevel = Integer.parseInt(request.getParameter("access_level"));
		}
		
		CreateUserDTO dto = new CreateUserDTO(name, middleName, surname, secondSurnameString, email, password, accessLevel, type);
		
		try {			
			service.createUser(dto);
			request.setAttribute("message", "Usuario creado exitosamente.");
		} catch (IllegalArgumentException e) {
			String msg = e.getMessage();
			if (msg != null && msg.startsWith("Nombre")) request.setAttribute("errorName", msg);
			else if (msg != null && msg.startsWith("Segundo nombre")) request.setAttribute("errorMiddleName", msg);
			else if (msg != null && msg.startsWith("Apellido") && !msg.startsWith("Apellido materno") && !msg.startsWith("Segundo apellido")) request.setAttribute("errorSurname", msg);
			else if (msg != null && (msg.startsWith("Segundo apellido") || msg.startsWith("Apellido materno"))) request.setAttribute("errorSecondSurname", msg);
			else if (msg != null && msg.startsWith("Correo")) request.setAttribute("errorEmail", msg);
			else if (msg != null && msg.startsWith("Contraseña")) request.setAttribute("errorPassword", msg);
			else request.setAttribute("error", msg);
			request.setAttribute("formValues", dto);
		} catch (RuntimeException e) {
			request.setAttribute("error", "Ha ocurrido un error, intente nuevamente.");
			request.setAttribute("formValues", dto);
		}
		
		request.getRequestDispatcher("/create-user.jsp").forward(request, response);
	}
}

package com.untec.user.web;

import java.io.IOException;

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
		this.repository = new UserRepositoryImpl();
		this.factory = new UserFactory();
		this.service = new UserService(factory);
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
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/create-user.jsp").forward(request, response);
		}
		
		request.setAttribute("message", "User created successfully");
		request.getRequestDispatcher("/create-user.jsp").forward(request, response);
	}
}

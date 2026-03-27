package com.untec.book.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.untec.book.application.BookService;
import com.untec.book.domain.Book;
import com.untec.book.domain.BookRepository;
import com.untec.book.infrastructure.persistence.BookRepositoryImpl;
import com.untec.shared.infrastructure.db.Database;
import com.untec.shared.infrastructure.db.MySQLDatabase;

@WebServlet("/book/home")
public class HomeServlet extends HttpServlet{
	private BookService service;
	private BookRepository repository;
	
	@Override
	public void init() throws ServletException {
		try {
			Database database = MySQLDatabase.getInstance();
			repository = new BookRepositoryImpl(database);
			service = new BookService(repository);
		} catch (SQLException | IOException e) {
			throw new ServletException("Error al inicializar el servlet", e);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("id") == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
			System.out.println(request.getParameter("page"));
		}
		int totalPages = service.getTotalPages(10);
		List<Book> books = service.getBooks(10, page);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("books", books);
		request.getRequestDispatcher("/student/home.jsp").forward(request, response);
	}
}

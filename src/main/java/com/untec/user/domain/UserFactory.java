package com.untec.user.domain;

import com.untec.user.application.dto.CreateUserDTO;

public class UserFactory {
	public User createUser(CreateUserDTO dto) {
		switch(dto.type) {
		case "student": 
			return new Student(dto.name, dto.middleName, dto.surname, dto.secondSurname, dto.email, dto.password);
		case "librarian":
			return new Librarian(dto.name, dto.middleName, dto.surname, dto.secondSurname, dto.email, dto.password, dto.accessLevel);
		default:
			throw new IllegalArgumentException("Tipo de usuario no valido");
		}
	}
}

package com.untec.user.application;

import com.untec.user.application.dto.CreateUserDTO;
import com.untec.user.application.dto.FindUserByEmailDTO;
import com.untec.user.domain.Student;
import com.untec.user.domain.User;
import com.untec.user.domain.UserFactory;
import com.untec.user.domain.UserRepository;
import com.untec.user.domain.exception.UserInactiveException;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class UserService {
	private final UserFactory factory;
	private final UserRepository repository;
	private final Argon2 argon2 = Argon2Factory.create();
	
	public UserService(UserFactory factory, UserRepository repository) {
		this.factory = factory;
		this.repository = repository;
	}

	public void createUser(CreateUserDTO dto) {
		if (dto == null) throw new IllegalArgumentException("No se puede crear un usuario nulo");
		User user = factory.createUser(dto);
		
		String hash = argon2.hash(2, 65536, 1,dto.password);
		user.changePassword(hash);
		repository.createUser(user);
	}
	
	public User findUserByEmail(FindUserByEmailDTO dto) {
		if (dto == null) throw new IllegalArgumentException("No se puede  buscar un usuario nulo");
		User user = repository.findByEmail(dto.email).orElseThrow(() -> new IllegalArgumentException("Correo electrónico inválido"));
		System.out.println("contraseña: " + user.getPassword());
		if(!argon2.verify(user.getPassword(), dto.password)) throw new IllegalArgumentException("Contraseña inválida");
		if(user instanceof Student student) {
			if(!student.isActive()) throw new UserInactiveException("Estudiante no tiene acceso a esta web");
		}
		
		return user;
	}
}

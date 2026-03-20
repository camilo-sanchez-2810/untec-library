package com.untec.user.application;

import com.untec.user.application.dto.CreateUserDTO;
import com.untec.user.domain.User;
import com.untec.user.domain.UserFactory;
import com.untec.user.domain.UserRepository;

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
}

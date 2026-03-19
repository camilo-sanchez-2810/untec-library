package com.untec.user.application;

import com.untec.user.application.dto.CreateUserDTO;
import com.untec.user.domain.User;
import com.untec.user.domain.UserFactory;

public class UserService {
	private UserFactory factory;
	
	public UserService(UserFactory factory) {
		this.factory = factory;
	}

	public void createUser(CreateUserDTO dto) {
		if (dto == null) throw new IllegalArgumentException("No se puede crear un usuario nulo");
		User user = factory.createUser(dto);
		
	}
}

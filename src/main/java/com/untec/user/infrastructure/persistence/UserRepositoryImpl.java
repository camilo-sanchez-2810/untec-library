package com.untec.user.infrastructure.persistence;

import com.untec.user.domain.UserRepository;
import com.untec.user.domain.User;

public class UserRepositoryImpl implements UserRepository {
	@Override
    public void save(User user) {
		System.out.println("Nuevo usuario guardado en base de datos: "+ user);
    }
}

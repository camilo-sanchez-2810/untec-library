package com.untec.user.domain;

import java.util.Optional;

public interface UserRepository {
	void createUser(User user);
	Optional<User> findByEmail(String email);
}

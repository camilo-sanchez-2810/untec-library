package com.untec.user.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.untec.user.domain.value.Email;
import com.untec.user.domain.value.Password;
import com.untec.user.domain.value.PersonName;

public class User {
	private final UUID id;
	private final LocalDateTime createdAt;
	private final PersonName name;
	private PersonName middleName;
	private final PersonName surname;
	private PersonName secondSurname;
	private Email email;
	private Password password;
	private final UserType type;

	public User(String name, String middleName, String surname, String secondSurname, String email, String password,
			UserType type) {
		this.id = UUID.randomUUID();
		this.createdAt = LocalDateTime.now();
		this.name = new PersonName("Nombre", name, 2);
		this.middleName = (middleName != null && !middleName.isBlank()) ? new PersonName("Segundo nombre", middleName, 2) : null;
		this.surname = new PersonName("Apellido", surname, 2);
		this.secondSurname = (secondSurname != null && !secondSurname.isBlank()) ? new PersonName("Segundo apellido", secondSurname, 2) : null;
		this.email = new Email("Correo Electronico", email);
		this.password = new Password("Contraseña", password);
		this.type = type;
	}

	public String getName() {
		return name.getValue();
	}

	public String getMiddleName() {
		return middleName != null ? middleName.getValue() : null;
	}

	public String getSurname() {
		return surname.getValue();
	}

	public String getSecondSurname() {
		return secondSurname != null ? secondSurname.getValue() : null;
	}

	public String getEmail() {
		return email.getValue();
	}

	public String getPassword() {
		return password.getValue();
	}

	public UUID getId() {
		return id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public UserType getType() {
		return type;
	}

	public void updateEmail(String newEmail) {
		this.email = new Email("Correo Electronico", newEmail);
	}

	public void changePassword(String newPassword) {
		this.password = new Password("Contraseña", newPassword);
	}

	public void updateMiddleName(String newMiddleName) {
		this.middleName = (newMiddleName != null && !newMiddleName.isBlank()) ? new PersonName("Segundo nombre", newMiddleName, 2) : null;
	}

	public void updateSecondSurname(String newSecondSurname) {
		this.secondSurname = (newSecondSurname != null && !newSecondSurname.isBlank()) ? new PersonName("Segundo apellido", newSecondSurname, 2) : null;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", middleName=" + middleName + ", surname=" + surname + ", secondSurname="
				+ secondSurname + ", email=" + email + ", password=" + password + ", type=" + type + "]";
	}
}

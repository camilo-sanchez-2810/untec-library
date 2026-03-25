package com.untec.user.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Librarian extends User {
	private final UUID librarianId;
	private final int accessLevel;

	public Librarian(String name, String middleName, String surname, String secondSurname, String email, String password) {
		super(name, middleName, surname, secondSurname, email, password, UserType.LIBRARIAN);
		this.librarianId = UUID.randomUUID();
		this.accessLevel = 1;
	}

	public Librarian(String name, String middleName, String surname, String secondSurname, String email, String password, Integer accessLevel) {
		super(name, middleName, surname, secondSurname, email, password, UserType.LIBRARIAN);
		this.librarianId = UUID.randomUUID();
		if (accessLevel == null)
			throw new IllegalArgumentException("El nivel de acceso no puede ser nulo");
		if (accessLevel < 1 || accessLevel > 2)
			throw new IllegalArgumentException("El nivel de acceso " + accessLevel + " no existe");
		this.accessLevel = accessLevel;
	}

	public Librarian(UUID id, UUID librarianId, String name, String middleName, String surname, String secondSurname,
			String email, String password, int accessLevel, LocalDateTime createdAt) {
		super(id, name, middleName, surname, secondSurname, email, password, UserType.LIBRARIAN, createdAt);
		this.librarianId = librarianId;
		this.accessLevel = accessLevel;
	}

	public UUID getLibrarianId() {
		return librarianId;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	@Override
	public String toString() {
		return "Librarian [librarianId=" + librarianId + ", accessLevel=" + accessLevel + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getMiddleName()=" + getMiddleName() + ", getSurname()=" + getSurname()
				+ ", getSecondSurname()=" + getSecondSurname() + ", getEmail()=" + getEmail() + ", getCreatedAt()="
				+ getCreatedAt() + ", getType()=" + getType() + "]";
	}
}

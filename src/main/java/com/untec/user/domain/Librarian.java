package com.untec.user.domain;

public class Librarian extends User {
	private final int accessLevel;

	public Librarian(String name, String middleName, String surname, String secondSurname, String email, String password) {
		super(name, middleName, surname, secondSurname, email, password, UserType.LIBRARIAN);
		this.accessLevel = 1;
	}

	public Librarian(String name, String middleName, String surname, String secondSurname, String email, String password, Integer accessLevel) {
		super(name, middleName, surname, secondSurname, email, password, UserType.LIBRARIAN);
		if (accessLevel == null)
			throw new IllegalArgumentException("El nivel de acceso no puede ser nulo");
		if (accessLevel < 1 || accessLevel > 2)
			throw new IllegalArgumentException("El nivel de acceso " + accessLevel + " no existe");
		this.accessLevel = accessLevel;
	}

	public int getAccessLevel() {
		return accessLevel;
	}
	
	@Override
	public String toString() {
		return "Librarian [accessLevel=" + accessLevel + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getMiddleName()=" + getMiddleName() + ", getSurname()=" + getSurname() + ", getSecondSurname()="
				+ getSecondSurname() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword()
				+ ", getCreatedAt()=" + getCreatedAt() + ", getType()=" + getType() + "]";
	}
}

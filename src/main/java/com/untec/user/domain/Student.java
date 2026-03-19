package com.untec.user.domain;

public class Student extends User {
	private Status status;
	private final int maxLoan = 5;

	public Student(String name, String middleName, String surname, String secondSurname, String email, String password) {
		super(name, middleName, surname, secondSurname, email, password, UserType.STUDENT);
		this.status = Status.ACTIVE;
	}

	public void suspend() {
		this.status = Status.SUSPENDED;
	}

	public void activate() {
		this.status = Status.ACTIVE;
	}

	public Status getStatus() {
		return status;
	}

	public int getMaxLoan() {
		return maxLoan;
	}
	
	@Override
	public String toString() {
		return "Student [status=" + status + ", getMaxLoan()=" + getMaxLoan() + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getMiddleName()=" + getMiddleName() + ", getSurname()=" + getSurname()
				+ ", getSecondSurname()=" + getSecondSurname() + ", getEmail()=" + getEmail() + ", getPassword()="
				+ getPassword() + ", getCreatedAt()=" + getCreatedAt() + ", getType()=" + getType() + "]";
	}
}

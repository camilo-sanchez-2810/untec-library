package com.untec.user.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Student extends User {
	private final UUID studentId;
	private Status status;
	private final int maxLoan = 5;

	public Student(String name, String middleName, String surname, String secondSurname, String email, String password) {
		super(name, middleName, surname, secondSurname, email, password, UserType.STUDENT);
		this.studentId = UUID.randomUUID();
		this.status = Status.ACTIVE;
	}

	public Student(UUID id, UUID studentId, String name, String middleName, String surname, String secondSurname,
			String email, String password, Status status, LocalDateTime createdAt) {
		super(id, name, middleName, surname, secondSurname, email, password, UserType.STUDENT, createdAt);
		this.studentId = studentId;
		this.status = status;
	}

	public boolean  isActive() {
		return status == Status.ACTIVE;
	}

	public UUID getStudentId() {
		return studentId;
	}

	public Status getStatus() {
		return status;
	}

	public int getMaxLoan() {
		return maxLoan;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", status=" + status + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getMiddleName()=" + getMiddleName() + ", getSurname()=" + getSurname()
				+ ", getSecondSurname()=" + getSecondSurname() + ", getEmail()=" + getEmail() + ", getCreatedAt()="
				+ getCreatedAt() + ", getType()=" + getType() + "]";
	}
}

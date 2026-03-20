package com.untec.user.application.dto;

public class CreateUserDTO {
	public final String name;
	public final String middleName; 
	public final String surname; 
	public final String secondSurname; 
	public final String email;
	public final String password;
	public final Integer accessLevel;
	public final String type;
	
	public CreateUserDTO(String name, String middleName, String surname, String secondSurname, String email,
			String password, Integer accessLevel, String type) {
		this.name = name;
		this.middleName = middleName;
		this.surname = surname;
		this.secondSurname = secondSurname;
		this.email = email;
		this.password = password;
		this.accessLevel = accessLevel;
		this.type = type;
	}

	public String getName() { return name; }
	public String getMiddleName() { return middleName; }
	public String getSurname() { return surname; }
	public String getSecondSurname() { return secondSurname; }
	public String getEmail() { return email; }
	public Integer getAccessLevel() { return accessLevel; }
	public String getType() { return type; }
}

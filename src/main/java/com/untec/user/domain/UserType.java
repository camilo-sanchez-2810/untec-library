package com.untec.user.domain;

public enum UserType {
	STUDENT("student", "Estudiante"),
	LIBRARIAN( "librarian", "Bibliotecario");

	private final  String type;
	private final String name;
	
	private UserType(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public static UserType fromType(String type) {
		for(UserType t : UserType.values())
			if(t.type.equalsIgnoreCase(type)) {
				return t;
			}
		
		throw new IllegalArgumentException("Tipo de usuario inválido: " + type);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
}

package com.untec.user.domain.value;

public class Email {
	private final String value;

	public Email(String value) {
		this.value = value;
	}
	
	public Email(String field, String value) {
		if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " no puede ser un campo vacio");
		if (!value.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) throw new IllegalArgumentException(field + " no es un correo valido");
		
		this.value=value;
	}
	
	public String getValue() {
		return value;
	}
}

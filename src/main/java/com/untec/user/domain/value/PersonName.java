package com.untec.user.domain.value;

public class PersonName {
	private final String value;
	
	public PersonName(String field, String value, int minimumLength) {
		if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " no puede ser un campo vacio");
		if (!value.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) throw new IllegalArgumentException(field + " solo puede contener letras");
		if (value.length() < minimumLength) throw new IllegalArgumentException(field + " no puede tener menos de " + minimumLength + " caracteres");
		
		this.value=value;
	}
	
	public String getValue() {
		return value;
	}
}

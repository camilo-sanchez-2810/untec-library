package com.untec.user.domain.value;

public class Password {
	private final String value;

	public Password(String hash) {
		this.value = hash;
	}
	
	public Password(String field, String value) {
		if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " no puede ser un campo vacio");
		if (value.length() < 8) throw new IllegalArgumentException(field + " no puede tener menos de 8 caracteres");
		if (!value.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$"))
			throw new IllegalArgumentException(field + " debe contener al menos una mayuscula, minuscula, numero y caracter especial");

		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

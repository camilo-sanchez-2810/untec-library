package com.untec.user.application.dto;

public class FindUserByEmailDTO {
	public final String email;
	public final String password;
	
	public FindUserByEmailDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}
}

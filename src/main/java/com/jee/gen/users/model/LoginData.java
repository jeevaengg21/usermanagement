package com.jee.gen.users.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginData implements Serializable{
	
	@NotNull(message = "email is mandatory")
	@NotBlank(message = "email is mandatory")
	private String email;
	@NotNull(message = "password is mandatory")
	@NotBlank(message = "password is mandatory")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

package com.plateformeDev.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
	 private String email;
	 private String password;
	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public Login() {
		super();
	}
	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}
	 

}

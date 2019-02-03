package com.demo.exam.entity;

import lombok.Data;

@Data
public class User {

	String username = "";
	String password;
	boolean enabled;

	@Override
	public String toString() {
		return "[ username=" + username + " enabled=" + enabled + "]";
	}

}

package com.demo.exam.entity;

import lombok.Data;

@Data
public class Option {


	String id;
	String description;

	public Option(String id, String desc) {
		this.id = id;
		this.description = desc;
	}

}

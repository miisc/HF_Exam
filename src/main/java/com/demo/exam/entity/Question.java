package com.demo.exam.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Question {

	long id, oid;
	private String type, description, options, answer, comment;
	List<Option> optionList;

	@Override
	public String toString() {
		return "Question [id=" + id + "	type=" + type + "	description=" + description + "	options=" + options
				+ "	answer=" + answer + "	comment=" + comment + "]";
	}

	public List<Option> getOptionList() {
		String[] strArray = options.split("B|C|D|E|F");
		this.optionList = new ArrayList<Option>();
		for (int i = 0; i < strArray.length; i++) {
			switch (i) {
			case 0:
				optionList.add(new Option("A", strArray[i]));
				break;
			case 1:
				optionList.add(new Option("B", "B" + strArray[i]));
				break;
			case 2:
				optionList.add(new Option("C", "C" + strArray[i]));
				break;
			case 3:
				optionList.add(new Option("D", "D" + strArray[i]));
				break;
			case 4:
				optionList.add(new Option("E", "E" + strArray[i]));
				break;
			case 5:
				optionList.add(new Option("F", "F" + strArray[i]));
				break;
			default:
				optionList.add(new Option("#", "No option"));
			}
		}
		return optionList;
	}

	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}
}

package com.veli.androidsu_09.accessingdb;

public class Person {

	private int id;
	private String name;

	public Person() {
		this.setName(name);
	}

	public Person(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

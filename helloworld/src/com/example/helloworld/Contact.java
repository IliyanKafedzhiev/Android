package com.example.helloworld;
public class Contact {

	private String name;
	private int id;
	private String phoneNumber;
	
	public void setName(String string) {
		name = string;
	}

	public void setID(int parseInt) {
		this.id = parseInt;
	}

	public void setPhoneNumber(String string) {
		phoneNumber = string;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public int getID()
	{
		return id;
	}
}

package model;

import java.io.Serializable;

public class User implements Serializable {
	private String name;
	private String pass;
	private String address;
	private int UID;

	public User() {
	}

	public User(String name, String pass, int UID) {
		this.name = name;
		this.pass = pass;
		this.UID = UID;
	}

	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;

	}

	public User(String name, String pass, String address) {
		this.name = name;
		this.pass = pass;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}

	public String getAddress() {
		return address;
	}

	public int getUID() {
		return UID;
	}
	public void setUID(int UID) {
		this.UID = UID;
	}
}

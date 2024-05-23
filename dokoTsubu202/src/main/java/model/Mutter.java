package model;

import java.io.Serializable;

public class Mutter implements Serializable {
	private int id;
	private String userName;
	private String title;
	private String text;

	public Mutter() {
	}

	public Mutter(String userName, String text) {
		this.userName = userName;
		this.text = text;
	}

	public Mutter(String userName, String title, String text) {
		this.userName = userName;
		this.title = title;
		this.text = text;
	}
	
	public Mutter(int id, String userName, String text) {
		this.id = id;
		this.userName = userName;
		this.text = text;
	}

	public Mutter(int id, String userName, String title, String text) {
		this.id = id;
		this.userName = userName;
		this.title = title;
		this.text = text;
	}


	public String getUserName() {
		return userName;
	}

	public String getText() {
		return text;
	}

	public String getTitle() {
		return title;
	}

	public int getId() {
		return id;
	}
	

}

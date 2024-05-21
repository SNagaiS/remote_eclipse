package model;

import java.io.Serializable;

public class Mutter implements Serializable {
	private int id;
	private String userName;
	private String title;
	private String text;
	private String time;

	public Mutter() {
	}

	public Mutter(String userName, String title, String text, String time) {
		this.userName = userName;
		this.title = title;
		this.text = text;
		this.time = time;

	}

	public Mutter(int id, String userName, String title, String text, String time) {
		this.id = id;
		this.userName = userName;
		this.title = title;
		this.text = text;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}
	public String getTime() {
		return time;
	}

}

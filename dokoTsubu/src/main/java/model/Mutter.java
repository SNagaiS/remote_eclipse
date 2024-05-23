package model;

import java.io.Serializable;

public class Mutter implements Serializable {
	private int uId;
	private int delete;
	private int num;
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

	public Mutter(int uId, int delete, int num, String userName, String title, String text, String time) {
		this.uId = uId;
		this.delete = delete;
		this.num = num;
		this.userName = userName;
		this.title = title;
		this.text = text;
		this.time = time;
	}
	
	public int getUId() {
		return uId;
	}
	public int getDelete() {
		return delete;
	}

	public int getNum() {
		return num;
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

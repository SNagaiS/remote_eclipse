package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//つぶやきに関する情報を持つ
public class Mutter implements Serializable {
	private int no;
	private String userName;
	private String title;
	private String text;
	private String date;
	private int good;
	private int bad;
	private String edit;
	

	
	public Mutter() {}
	
	public Mutter(int no, String userName, String text, String title, String date, int good, int bad, String edit) {
		this.no  = no;
		this.userName = userName;
		this.title = title;
		this.text = text;
		this.date = date;
		this.good = good;
		this.bad = bad;
		this.edit = edit;
	}
	
	public Mutter(int no, String userName, String text, String title, String date, String edit) {
		this.no  = no;
		this.userName = userName;
		this.title = title;
		this.text = text;
		this.date = date;
		this.edit = edit;
	}
	
	public Mutter(String userName, String text, String title, Date date) {
		this.userName = userName;
		this.text = text;
		this.title = title;
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm");
		this.date = sdf.format(date);
		
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
	
	public String getDate() {
		return date;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public String getEdit() {
		return edit;
	}
	
	public int getGood() {
		return good;
	}
	public int getBad() {
		return bad;
	}
	
}

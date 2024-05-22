package model;

import java.io.Serializable;

public class User implements Serializable {
	private int no;
	private String name; //ユーザー名
	private String pass; //パスワード
	private int flag;
	
	
	public User() {};
	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}
	public User(int no,String name, String pass, int flag) {
		this.no = no;
		this.name = name;
		this.pass = pass;
		this.flag = flag;
	}
	
	public int getNo() { return no;}
	public String getName() {return name;}
	public String getPass() {return pass;}
	public int getFlag() { return flag;}
}

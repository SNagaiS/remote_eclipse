package model;
import java.io.Serializable;

public class Human implements Serializable{
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;
	private int age;
	
	public Human() {
	}
	public Human(String name, int age) {
		this.name = name;
		this.age=age;
		
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
		

}

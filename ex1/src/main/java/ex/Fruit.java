package ex;

public class Fruit implements java.io.Serializable{
private String name;
private int price;
public Fruit () {}
public Fruit (String name, int price) {
	this.name = name;
	this.price = price;
	}
public String getName() {return name;}
public int getPrice() {return price;}
	
}


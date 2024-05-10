package model;

import java.io.Serializable;

public class Health implements Serializable{
private double height, weight, bmi;
private String bodyType, name;
public double getHeight() {
	return this.height;
}
public void setHeight(double height) {
	this.height = height;
}
public double getWeight() {
	return this.weight;
}
public void setWeight(double weight) {
	this.weight = weight;
}
public String getName() {
	return this.name;
}
public void setName(String name) {
	this.name = name;
}
public double getBmi() {
	return this.bmi;
}
public void setBmi(double bmi) {
	this.bmi = bmi;
}
public String getBodyType() {
	return this.bodyType;
}
public void setBodyType(String bodyType) {
	this.bodyType = bodyType;
}
}


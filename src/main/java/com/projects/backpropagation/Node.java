package com.projects.backpropagation;

import java.io.Serializable;

public class Node implements Serializable{
	private static final long serialVersionUID = 1L;
	private double data;
	private double target,error;
	private String name;
	
	public Node(String name) {
		this.name = name;
	}
	public Node(String name, double data) {
		this.name = name;
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public Double getData() {
		return data;
	}
	public void setData(double data) {
		this.data = data;
	}
	public Double getTarget() {
		return target;
	}
	public void setTarget(double target) {
		this.target = target;
	}
	public Double getError() {
		return error;
	}
	public void setError(double error) {
		this.error = error;
	}
}

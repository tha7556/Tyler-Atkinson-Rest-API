package com.projects.backpropagation;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	private void writeObject(ObjectOutputStream o)
	{  
		    
		try 
		{
			o.defaultWriteObject();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	private void readObject(ObjectInputStream o) {    
		try {
			o.defaultReadObject();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
}

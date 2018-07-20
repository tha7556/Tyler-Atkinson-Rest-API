package com.api.objects.backpropagation;

public class Node {
    private String name;
    private double data;

    public Node() {

    }
    public Node(String name, double data) {
        this.data = data;
        this.name = name;
    }
    public double getData() {
        return data;
    }
    public String getName() {
        return name;
    }
    public void setData(double data) {
        this.data = data;
    }
    public void setName(String name) {
        this.name = name;
    }
}

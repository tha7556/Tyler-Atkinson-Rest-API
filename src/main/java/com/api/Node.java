package com.api;

public class Node {
    private final String name;
    private final double data;

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
}

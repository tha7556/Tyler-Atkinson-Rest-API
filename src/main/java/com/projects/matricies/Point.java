package com.projects.matricies;

/**
 * A Point class used for representing (x,y) coordinates
 * @author Tyler Atkinson
 */
public class Point {
	private double x, y;
	private int size = 2;
	/**
	 * Creates a point based on (x,y) coordinates
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Gets the X coordinate
	 * @return The X coordinate
	 */
	public double getX() {
		return x;
	}
	/**
	 * Gets the Y coordinate
	 * @return The Y coordinate
	 */
	public double getY() {
		return y;
	}
	/**
	 * Returns the (x,y) coordinates as an array: [x,y]
	 * @return The coordinates as an array
	 */
	public double[] getAsArray() {
		return new double[] {x,y};
	}
	/**
	 * Changes the X value to a new one
	 * @param x The new X value
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * Changes the Y value to a new one
	 * @param y The new Y value
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * Returns this Point as a String in the form: (x,y)
	 */
	public String toString() {
		return "("+x+","+y+")";
	}
	/**
	 * Subtracts another Point from this one
	 * @param other The Point to subtract
	 * @return A new Point based on the difference between the two Points
	 */
	public Point subtract(Point other) {
		return new Point(x-other.getX(),y-other.getY());
	}
	/**
	 * Gets the size of this Point/Vector
	 * @return The Size of this Point
	 */
	public int size() {
		return size;
	}
}

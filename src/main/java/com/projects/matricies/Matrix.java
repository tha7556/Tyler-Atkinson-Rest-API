package com.projects.matricies;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Matrix class used for Matrix calculations
 * @author Tyler Atkinson
 */
public class Matrix {
	private double[][] matrix;
	private int width, height;

	public Matrix(double[][] matrix) {
		width = matrix[0].length;
		height = matrix.length;
		this.matrix = matrix;
	}
	/**
	 * Creates a blank Matrix with null values of the designated size
	 * @param rows The number of rows in the new Matrix
	 * @param columns The number of columns in the new Matrix
	 */
	public Matrix(int rows, int columns) {
		width = columns;
		height = rows;
		matrix = new double[rows][columns];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				matrix[i][j] = 0.0;
			}
		}
	}
	/**
	 * Gets an array of the desired row
	 * @param index The index of the row
	 * @return An array containing the values of the given row
	 */
	public double[] getRow(int index) {
		double[] row = new double[width];
		for(int i = 0; i < matrix[index].length; i++) {
			row[i] = get(index,i);
		}
		return row;
	}
	/**
	 * Gets an array of the desired column
	 * @param index The index of the column
	 * @return An array containing the values of the given column
	 */
	public double[] getColumn(int index) {
		double column[] = new double[height];
		for(int i = 0; i < matrix.length; i++) {
			column[i] = get(i,index);
		}
		return column;
	}
	/**
	 * @return An int value representing the height of the Matrix
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @return An int value representing the width of the Matrix
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * Gets the value at the given location
	 * @param row The row containing the value
	 * @param column The column containing the value
	 * @return The value at the given location
	 */
	public double get(int row, int column) {
		return matrix[row][column];
	}
	/**
	 * Sets the given position to the given value
	 * @param row The desired row
	 * @param column The desired column
	 * @param value The value to insert
	 */
	public void set(int row, int column, double value) {
		this.matrix[row][column] = value;
	}
	/**
	 * Prints the Matrix to the console
	 */
	public void print() {
		for(double[] arr : matrix) {
			for(int i = 0; i < arr.length; i++) {
				if(i < arr.length-1)
					System.out.print(arr[i]+", ");
				else
					System.out.println(arr[i]);
			}
		}
	}
	/**
	 * Prints the Matrix to the given file in a csv format
	 * @param fileName The file name to print to (include .csv)
	 */
	public void printToFile(String fileName) {
		File file = new File(fileName);
		FileWriter fWriter = null;
		PrintWriter pWriter = null;
		try {
			fWriter = new FileWriter(file);
			pWriter = new PrintWriter(fWriter);
		} catch(Exception e) {
			e.printStackTrace();
		}
		for(double[] arr : matrix) {
			for(int i = 0; i < arr.length; i++) {
				pWriter.print(arr[i]+",");
				
			}
			pWriter.println();
		}
		try {
			pWriter.close();
			fWriter.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Makes a copy of the Matrix
	 * @return A copy of the Matrix
	 */
	public Matrix getCopy() {
		Matrix result = new Matrix(height,width);
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				result.set(i, j, get(i,j));
			}
		}
		return result;
	}
	/**
	 * Multiplies the Matrix by a given scalar
	 * @param scalar The scalar to multiply the Matrix by
	 * @return The resulting Matrix of the multiplication
	 */
	public Matrix multiply(double scalar) { 
		Matrix matrix = new Matrix(height, width);
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				matrix.set(y,x,get(y,x) * scalar);
			}
		}
		return matrix;
	}
	/**
	 * Multiplies this Matrix by another Matrix
	 * @param other The Matrix to multiply this one by
	 * @return The resulting Matrix of the multiplication
	 */
	public Matrix multiply(Matrix other) { 
		if(width == other.getHeight()) {
		Matrix matrix = new Matrix(height,other.getWidth());
			for(int i = 0; i < height; i++) { //each row
				for(int j = 0; j < other.getWidth(); j++) { //each column
					for(int y = 0; y < width; y++) {
						double val = matrix.get(i, j) + (get(i,y) * other.get(y,j));
						matrix.set(i, j, val);
					}
				}
				
			}
			return matrix;
		}
		else {
			throw new RuntimeException("A\'s width must be equal to B\'s height!");
		}
	}
	/**
	 * Adds this Matrix to another
	 * @param other The Matrix to add to this one
	 * @return The resulting Matrix of the addition
	 */
	public Matrix add(Matrix other) {
		if(width == other.getWidth() && height == other.getHeight()) {
			Matrix matrix = new Matrix(height, width);
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					matrix.set(y, x, get(y,x)+other.get(y, x));					
				}
			}
			return matrix;
		}
		else {
			throw new RuntimeException("Sizes of the two Matrices must be equal!");
		}
		
	}
	/**
	 * Subtracts another Matrix from this one
	 * @param other The Matrix to subtract from this one
	 * @return The resulting Matrix of the subtraction
	 */
	public Matrix subtract(Matrix other) {
		if(width == other.getWidth() && height == other.getHeight()) {
			Matrix matrix = new Matrix(height, width);
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					matrix.set(y, x, get(y,x)-other.get(y, x));
				}
			}
			return matrix;
		}
		else {
			throw new RuntimeException("Sizes of the two Matrices must be equal!");
		}
		
	}
	/**
	 * Used to determine if it is a square Matrix
	 * @return True if it is Square
	 */
	public boolean isSquare() {
		return width == height;
	}
	/**
	 * Gets the identity Matrix of the same size as the current Matrix
	 * @return The identity Matrix
	 */
	public Matrix getIdentityMatrix() {
		if(isSquare()) {
			Matrix matrix = new Matrix(height,width);
			for(int i = 0; i < width; i++) {
				matrix.set(i, i, 1);
			}
			return matrix;
		}
		else {
			System.out.println("Matrix must be square!");
			return null;
		}
		
	}
	/**
	 * Creates a new Matrix of the transpose for this Matrix
	 * @return The Transpose Matrix
	 */
	public Matrix getTranspose() {
		Matrix result = new Matrix(width, height);
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				result.set(x, y, matrix[y][x]);
			}
		}
		return result;
	}
	/**
	 * Swaps two rows inside of the Matrix
	 * @param rowA The index of the first row to swap
	 * @param rowB The index of the second row to swap
	 */
	public void swapRows(int rowA, int rowB) {
		double[] temp = matrix[rowA];
		matrix[rowA] = matrix[rowB];
		matrix[rowB] = temp;
	}
	/**
	 * Finds the Determinant (if there is one) and returns 0.0 if there isn't one
	 * @return The Determinant or 0.0 if there isn't one
	 */
	public double findDeterminant() {
		Matrix m = getCopy();
		int r = 0;
 		for(int j = 0; j < width-1; j++) {
			int p = m.findPivot(j);
			if(m.get(p,j) == 0.0) {
				return 0.0;
			}
			if(p > j) {
				m.swapRows(p,j);
				r++;
			}
			for(int i = j+1; i < height; i++) {
				double d = m.get(i,j)/m.get(j,j);
				for(int k = j; k < width; k++) {
					double num = m.get(i,k) - (d * m.get(j,k));
					m.set(i,k,num);
				}
			}
		}
 		double val = 1.0;
 		for(int n = 0; n < width; n++) {
 			val *= m.get(n,n);
 		}
 		double result = Math.pow(-1, r) * val;
 		return result;
	}
	/**
	 * Creates a new Matrix of the Inverse for this Matrix
	 * @return The Inverse Matrix of this Matrix
	 */
	public Matrix getInverse() {
		Matrix c = combineWith(getIdentityMatrix());
		for(int j = 0; j < c.getHeight(); j++) {
			int p = c.findPivot(j);
			if(c.get(p, j) == 0.0) 
				return null;
			if(p > j) {
				c.swapRows(p, j);
			}
			double cJJ = c.get(j, j);
			for(int k = 0; k < c.getWidth(); k++) {
				c.set(j, k, (c.get(j, k)/cJJ));
			}
			for(int i = 0; i < c.getHeight(); i++) { 
				if(i != j) {
					double cIJ = c.get(i, j);
					for(int k = 0; k < c.getWidth(); k++) {
						double val = c.get(i, k) - cIJ*c.get(j, k);
						c.set(i, k, val);
					}
				}
			}
		}
		Matrix result = new Matrix(height,width);
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				result.set(y, x, c.get(y, x+width));
			}
		}
		return result;
	}
	/**
	 * Appends two Matrices together assuming that they are equal height
	 * @param other The other Matrix to append to the end of this one
	 * @return The combined Matrix of this one and another
	 */
	public Matrix combineWith(Matrix other) {
		if(height != other.getHeight()) {
			throw new RuntimeException("Heights do not match!");
		}
		int newWidth = width + other.getWidth();
		ArrayList<ArrayList<Double>> newMatrix = new ArrayList<ArrayList<Double>>();
		for(int y = 0; y < height; y++) {
			ArrayList<Double> row = new ArrayList<Double>(newWidth);
			for(double d : matrix[y]) {
				row.add(d);
			}
			for(double d : other.getRow(y)) {
				row.add(d);
			}
			newMatrix.add(row);
		}
		Matrix result = new Matrix(height,newWidth);
		for(int y = 0; y < result.getHeight(); y++) {
			for(int x = 0; x < result.getWidth(); x++) {
				result.set(y, x, newMatrix.get(y).get(x));
			}
		}
		return result;
	}
	/**
	 * Finds the pivot which is used in the findDeterminant function
	 * @param index The index of the current row
	 * @return The index of the pivot row
	 */
	private int findPivot(int index) {
		//System.out.println(index);
		int result = -1;
		double max = -1.0;
		for(int i = 0; i < height; i++) {
			if(Math.abs(get(i,index)) > max) {
				max = Math.abs(get(i,index));
				result = i;
			}
		}
		return result;
	}
	/**
	 * Creates a Matrix based on a file with columns seperated by tabs and rows by new lines
	 * @param fileName The name of the file containing the Matrix data
	 * @return The resulting Matrix from the file
	 */
	public static Matrix getFromFile(String fileName) {
		ArrayList<ArrayList<Double>> matrix = new ArrayList<ArrayList<Double>>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine()) {
				ArrayList<Double> array = new ArrayList<Double>();
				String line = scanner.nextLine();
				String[] arr = line.split("\t");
				for(int i = 0; i < arr.length; i++) {
					array.add(Double.parseDouble(arr[i].trim()));
				}
				matrix.add(array);
			}
			scanner.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		Matrix result = new Matrix(matrix.size(),matrix.get(0).size());
		for(int y = 0; y < result.getHeight(); y++) {
			for(int x = 0; x < result.getWidth(); x++) {
				result.set(y, x, matrix.get(y).get(x));
			}
		}
		return result;
	} 
}

package com.api.controllers;

import com.projects.matricies.Matrix;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatrixController {
    @RequestMapping(value = {"/matrix/add"}, method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody double[][][] matrices) {
        if(matrices == null || matrices.length != 2 || matrices[0].length < 1 || matrices[1].length < 1 || matrices[0][0].length < 1 || matrices[0][1].length < 1) {
            return new ResponseEntity<>("Must enter exactly 2 matrices, both with at least one value",HttpStatus.BAD_REQUEST);
        }
        Matrix a = new Matrix(matrices[0]);
        Matrix b = new Matrix(matrices[1]);
        if(a.getWidth() != b.getWidth() || a.getHeight() != b.getHeight()) {
            return new ResponseEntity<>("The matrices must be the same size",HttpStatus.BAD_REQUEST);
        }
        Matrix result = a.add(b);
        return new ResponseEntity<>(result.getMatrix(), new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value = {"/matrix/subtract"}, method = RequestMethod.POST)
    public ResponseEntity<?> subtract(@RequestBody double[][][] matrices) {
        if(matrices == null || matrices.length != 2 || matrices[0].length < 1 || matrices[1].length < 1 || matrices[0][0].length < 1 || matrices[0][1].length < 1) {
            return new ResponseEntity<>("Must enter exactly 2 matrices, both with at least one value",HttpStatus.BAD_REQUEST);
        }
        Matrix a = new Matrix(matrices[0]);
        Matrix b = new Matrix(matrices[1]);
        if(a.getWidth() != b.getWidth() || a.getHeight() != b.getHeight()) {
            return new ResponseEntity<>("The matrices must be the same size",HttpStatus.BAD_REQUEST);
        }
        Matrix result = a.subtract(b);
        return new ResponseEntity<>(result.getMatrix(), new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value = {"/matrix/multiply"}, method = RequestMethod.POST)
    public ResponseEntity<?> multiply(@RequestBody double[][][] matrices) {
        if(matrices == null || matrices.length != 2 || matrices[0].length < 1 || matrices[1].length < 1 || matrices[0][0].length < 1 || matrices[0][1].length < 1) {
            return new ResponseEntity<>("Must enter exactly 2 matrices, both with at least one value",HttpStatus.BAD_REQUEST);
        }
        Matrix a = new Matrix(matrices[0]);
        Matrix b = new Matrix(matrices[1]);
        Matrix result = a.multiply(b);
        if(result == null) {
            return new ResponseEntity<>("A\'s width must be equal to B\'s height!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getMatrix(), new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value = {"/matrix/multiplyScalar/{scalar}"}, method = RequestMethod.POST)
    public ResponseEntity<?> multiply(@RequestBody double[][] matrix, @PathVariable double scalar) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return new ResponseEntity<>("Must enter a Matrix with at least one value",HttpStatus.BAD_REQUEST);
        }
        Matrix a = new Matrix(matrix);
        Matrix result = a.multiply(scalar);
        return new ResponseEntity<>(result.getMatrix(), new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value = {"/matrix/identity/{width}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getIdentityMatrix(@PathVariable int width) {
        if(width < 1)
            return new ResponseEntity<>("Width must be greater than zero",HttpStatus.BAD_REQUEST);
        Matrix result = new Matrix(width,width).getIdentityMatrix();
        return new ResponseEntity<>(result.getMatrix(), new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value = {"/matrix/transpose"}, method = RequestMethod.POST)
    public ResponseEntity<?> getTranspose(@RequestBody double[][] matrix) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return new ResponseEntity<>("Must enter a Matrix with at least one value",HttpStatus.BAD_REQUEST);
        }
        Matrix result = new Matrix(matrix).getTranspose();
        return new ResponseEntity<>(result.getMatrix(), new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value = {"/matrix/determinant"}, method = RequestMethod.POST)
    public ResponseEntity<?> getDeterminant(@RequestBody double[][] matrix) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return new ResponseEntity<>("Must enter a Matrix with at least one value",HttpStatus.BAD_REQUEST);
        }
        double result = new Matrix(matrix).findDeterminant();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value = {"/matrix/inverse"}, method = RequestMethod.POST)
    public ResponseEntity<?> getInverse(@RequestBody double[][] matrix) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return new ResponseEntity<>("Must enter a Matrix with at least one value",HttpStatus.BAD_REQUEST);
        }
        Matrix result = new Matrix(matrix).getInverse();
        return new ResponseEntity<>(result.getMatrix(), new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value = {"/matrix/append"}, method = {RequestMethod.POST})
    public ResponseEntity<?> append(@RequestBody double[][][] matrices) {
        if(matrices == null || matrices.length != 2 || matrices[0].length < 1 || matrices[1].length < 1 || matrices[0][0].length < 1 || matrices[0][1].length < 1) {
            return new ResponseEntity<>("Must enter exactly 2 matrices, both with at least one value",HttpStatus.BAD_REQUEST);
        }
        Matrix a = new Matrix(matrices[0]);
        Matrix b = new Matrix(matrices[1]);
        if(a.getHeight() != b.getHeight())
            return new ResponseEntity<>("The heights of the Matrices must be equal", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(a.combineWith(b).getMatrix(), new HttpHeaders(), HttpStatus.OK);
    }
}

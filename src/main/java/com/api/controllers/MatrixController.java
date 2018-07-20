package com.api.controllers;

import com.projects.matricies.Matrix;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatrixController {
    @RequestMapping(value = {"/matrix/add"}, method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody double[][][] matrices) {
        if(matrices.length != 2 || matrices[0].length < 1 || matrices[1].length < 1 || matrices[0][0].length < 1 || matrices[0][1].length < 1) {
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
}

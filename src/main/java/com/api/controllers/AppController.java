package com.api.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*")
@RestController
public class AppController {
    @RequestMapping(value= {"/status"}, method = GET)
    public ResponseEntity<Status> getStatus() {
        return new ResponseEntity<>(new Status("Server is online"), new HttpHeaders(), HttpStatus.OK);
    }
    private class Status {
        private String value;
        public Status(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
}

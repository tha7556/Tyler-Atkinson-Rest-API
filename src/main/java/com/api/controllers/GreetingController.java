package com.api.controllers;

import com.api.Greeting;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = {"/greeting/{name}"}, method=GET)
    public Greeting greeting(@PathVariable String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template,name));
    }
}

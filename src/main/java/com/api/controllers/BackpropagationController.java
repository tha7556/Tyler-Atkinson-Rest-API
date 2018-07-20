package com.api.controllers;

import com.api.Backpropagation;
import com.api.Node;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BackpropagationController {

    @RequestMapping(value = {"/backpropagation/"}, method = POST)
    public Backpropagation getNetwork(@RequestBody BackpropagationParams params) {
        return new Backpropagation(params);
    }
    @RequestMapping(value={"/backpropagation/run"}, method = POST)
    public double[] runNetwork(@RequestBody Backpropagation network) {
        return network.run();
    }
}

package com.api.controllers;

import com.api.objects.backpropagation.Backpropagation;
import com.api.objects.backpropagation.BackpropagationParams;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BackpropagationController {

    @RequestMapping(value = {"/backpropagation"}, method = POST)
    public ResponseEntity<?> getNetwork(@RequestBody BackpropagationParams params) {
        if(params.getInput().length < 1 || params.getNumHidden() < 1 || params.getExpectedOutput().length < 1 || params.getInput()[0].length < 1 || params.getExpectedOutput()[0].length < 1 || params.getTargetError() < 0)
            return new ResponseEntity<>("Something is wrong with the input parameters", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new Backpropagation(params), new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value={"/backpropagation/run"}, method = POST)
    public ResponseEntity<?> runNetwork(@RequestBody Backpropagation network) {
        int expectedWeights = (network.getInputNodes().length * network.getHiddenNodes().length) + network.getHiddenNodes().length + (network.getHiddenNodes().length * network.getOutputNodes().length) + network.getOutputNodes().length;
        if(network.getInputNodes().length < 1 || network.getHiddenNodes().length < 1 || network.getOutputNodes().length < 1)
            return new ResponseEntity<>("There is a problem with the number of nodes in the given network",HttpStatus.BAD_REQUEST);
        if(network.getWeightMap().size() != expectedWeights)
            return new ResponseEntity<>("There is a problem with the number of weights in the given network",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(network.run(), new HttpHeaders(), HttpStatus.OK);
    }
}

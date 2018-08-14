package com.api.controllers;

import com.projects.tsp.Salesman.BlacksmithSalesman;
import com.projects.tsp.Salesman.GeneticSalesman;
import com.projects.tsp.utility.City;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@CrossOrigin(origins = "*")
@RestController
public class SalesmanController {

    @RequestMapping(value = {"/salesman/genetic"}, method = RequestMethod.POST)
    public ResponseEntity<?> calculateGenetic(@RequestBody City[] cities) {
        if(cities == null || cities.length < 2 || cities.length > 26)
            return new ResponseEntity<>("The number of cities should be between [2, 26]",HttpStatus.BAD_REQUEST);
        GeneticSalesman salesman = new GeneticSalesman(cities);
        salesman.compute();
        return new ResponseEntity<>(salesman.getBestRoute().getCities(), new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value = {"/salesman/genetic/{numCities}"},method = RequestMethod.GET)
    public ResponseEntity<?> calculateGenetic(@PathVariable long numCities) {
        if(numCities < 2 || numCities > 26)
            return new ResponseEntity<>("The number of cities should be between [2, 26]",HttpStatus.BAD_REQUEST);
        City[] cities = new City[(int)numCities];
        Random random = new Random();
        for(int i = 0; i < numCities; i++) {
            cities[i] = new City(""+(char)('A'+i),random.nextDouble(), random.nextDouble());
        }
        GeneticSalesman salesman = new GeneticSalesman(cities);
        salesman.compute();
        return new ResponseEntity<>(salesman.getBestRoute().getCities(), new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value = {"/salesman/annealing"}, method = RequestMethod.POST)
    public ResponseEntity<?> calculateSimulatedAnnealing(@RequestBody City[] cities) {
        if(cities == null || cities.length < 2 || cities.length > 26)
            return new ResponseEntity<>("The number of cities should be between [2, 26]",HttpStatus.BAD_REQUEST);
        BlacksmithSalesman salesman = new BlacksmithSalesman(cities);
        salesman.compute();
        return new ResponseEntity<>(salesman.getBestRoute().getCities(), new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value = {"/salesman/annealing/{numCities}"},method = RequestMethod.GET)
    public ResponseEntity<?> calculateSimulatedAnnealing(@PathVariable long numCities) {
        if(numCities < 2 || numCities > 26)
            return new ResponseEntity<>("The number of cities should be between [2, 26]",HttpStatus.BAD_REQUEST);
        City[] cities = new City[(int)numCities];
        Random random = new Random();
        for(int i = 0; i < numCities; i++) {
            cities[i] = new City(""+(char)('A'+i),random.nextDouble(), random.nextDouble());
        }
        BlacksmithSalesman salesman = new BlacksmithSalesman(cities);
        salesman.compute();
        return new ResponseEntity<>(salesman.getBestRoute().getCities(), new HttpHeaders(), HttpStatus.OK);
    }

}

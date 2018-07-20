package com.api.controllers;

import com.projects.tsp.Salesman.BlacksmithSalesman;
import com.projects.tsp.Salesman.GeneticSalesman;
import com.projects.tsp.utility.City;
import com.projects.tsp.utility.Route;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesmanController {

    @RequestMapping(value = {"/salesman/genetic"}, method = RequestMethod.POST)
    public Route calculateGenetic(@RequestBody City[] cities) {
        GeneticSalesman salesman = new GeneticSalesman(cities);
        salesman.compute();
        return salesman.getBestRoute();
    }
    @RequestMapping(value = {"/salesman/annealing"}, method = RequestMethod.POST)
    public Route calculateSimulatedAnnealing(@RequestBody City[] cities) {
        BlacksmithSalesman salesman = new BlacksmithSalesman(cities);
        salesman.compute();
        return salesman.getBestRoute();
    }
}

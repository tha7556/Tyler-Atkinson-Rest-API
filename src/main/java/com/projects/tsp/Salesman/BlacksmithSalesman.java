package com.projects.tsp.Salesman;


import com.projects.tsp.utility.City;
import com.projects.tsp.utility.Route;

import java.util.Random;

public class BlacksmithSalesman extends Salesman {
    private Route currentRoute;
    private double temperature = 1000.0, coolingRate = 0.00001;
    private Random rand;
    public BlacksmithSalesman(City[] cities) {
        super(cities);
        currentRoute = new Route(cities);
        rand = new Random();
    }
    public void compute() {
        if(cities.length < 4) {
            return;
        }
        while(temperature > 0.00000001) {
            Route newRoute = currentRoute;
            int swapIndex1 = (int)(rand.nextDouble()*(cities.length-1)+1);
            int swapIndex2 = (int)(rand.nextDouble()*(cities.length-1)+1);
            while(swapIndex1 == swapIndex2) {
                swapIndex1 = (int)(rand.nextDouble()*(cities.length-1)+1);
                swapIndex2 = (int)(rand.nextDouble()*(cities.length-1)+1);
            }
            newRoute = newRoute.swap(swapIndex1,swapIndex2);
            if(acceptanceProbability(currentRoute.getDistance(),newRoute.getDistance()) > rand.nextDouble()) {
                currentRoute = newRoute;
                compareRoute(currentRoute);
            }
            temperature *= 1.0 - coolingRate;
        }
    }
    public double acceptanceProbability(double currentEnergy, double newEnergy) {
        if(newEnergy < currentEnergy)
            return 1.0;
        return Math.exp((currentEnergy - newEnergy) / temperature);
    }
}
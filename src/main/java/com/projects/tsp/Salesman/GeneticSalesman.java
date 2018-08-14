package com.projects.tsp.Salesman;



import com.projects.tsp.utility.City;
import com.projects.tsp.utility.Route;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
/**
 * Solution to the TSP which uses a Genetic Algorithm
 * @author Tyler Atkinson
 */
public class GeneticSalesman extends Salesman {
    private final static int POPULATION_SIZE = 200;
    private final static double MUTATION_RATE = 0.01;
    private double totalFitness;
    private int generations,lastChanged, delta;
    private static Random rand;
    private Route[] routes;
    /**
     * Creates a new Salesman out of an Array of Cities
     * @param cities The Array of Cities
     * @param delta The number of generations in between changes in besRoute to determine convergence
     */
    public GeneticSalesman(City[] cities, int delta) {
        super(cities);
        generateInitialRoutes();
        this.delta = delta;
        rand = new Random();
    }
    /**
     * Creates a new Salesman out of an Array of Cities
     * @param cities The Array of Cities
     */
    public GeneticSalesman(City[] cities) {
        this(cities, 8000);
    }

    /**
     * Generates the initial population of random Routes
     */
    private void generateInitialRoutes() {
        routes = new Route[POPULATION_SIZE];
        totalFitness = 0.0;
        for(int i = 0; i < POPULATION_SIZE; i++) {
            Route r = new Route(shuffleArray(cities,true));
            routes[i] = r;
            totalFitness += r.calcFitness();
        }

    }
    /**
     * Begins evaluating permutations generated by the genetic algorithm
     */
    public void compute() {
        mean = 0;
        sum = 0;
        sqrSum = 0;
        generations = 0;
        while(generations-lastChanged < delta) {
            evolve();
        }
    }

    /**
     * Analyzes every Route in the population using the compareRoute function
     */
    private void analyzeRoutes() {
        for(Route route : routes)
            compareRoute(route);
    }
    /**
     * Updates the Displayed Route in the window. Also updates the lastChanged variable
     * @param route The new Route to display
     */
    public void updateRoute(Route route) {
        lastChanged = generations;
    }
    /**
     * Evolves the current population of Routes into the next generation
     */
    private void evolve() {
        generations++;
        Route[] newRoutes = new Route[routes.length];
        totalFitness = 0.0;
        newRoutes[0] = bestRoute;
        for(int i = 1; i < routes.length; i++) {
            Route[] parents = selectParents();
            Route child = createChild(parents[0],parents[1]);
            totalFitness += child.calcFitness();
            newRoutes[i] = mutate(child);
        }
        routes = newRoutes;
        analyzeRoutes();
    }

    /**
     * Selects 2 parents from the population to create a new child
     * @return A Route[] containing 2 Routes representing the parents
     */
    private Route[] selectParents() {
        Route father;
        Route mother;
        //father
        double target = rand.nextDouble()*totalFitness;
        double current = 0.0;
        int i = 0;
        while(current < target) {
            if(i == routes.length)
                i = 0;
            current += routes[i].calcFitness();
            i++;
        }
        i--;
        if(i < 0)
            i = routes.length-1;
        father = routes[i];
        //mother
        target = rand.nextDouble()*totalFitness;
        current = 0.0;
        i = 0;
        while(current < target) {
            if(i == routes.length)
                i = 0;
            current += routes[i].calcFitness();
            i++;
        }
        i--;
        if(i < 0)
            i = routes.length-1;
        mother = routes[i];
        return new Route[] {father, mother};
    }

    /**
     * Creates a child from the 2 parent Routes
     * @param father The first Route to act as a parent
     * @param mother The second Route to act as a parent
     * @return The new child Route
     */
    private Route createChild(Route father, Route mother) {
        City[] arr = new City[cities.length];
        int start = (int)(rand.nextDouble()*cities.length);
        int end = (int)(rand.nextDouble()*cities.length);
        if(end < start) {
            int temp = start;
            start = end;
            end = temp;
        }
        for(int i = 1; i < end; i++) {
            if(i > start) {
                arr[i] = father.getCities()[i];
            }
        }
        List childList = Arrays.asList(arr);
        for(City c : mother.getCities()) {
            if(!childList.contains(c)) {
                for(int i = 0; i < arr.length; i++) {
                    if(arr[i] == null) {
                        arr[i] = c;
                        childList.set(i,c);
                        break;
                    }
                }
            }
        }
        return new Route(arr);
    }

    /**
     * Randomly mutates a Route based on the MUTATION_RATE
     * @param route The Route to mutate
     * @return The Route after mutation
     */
    private Route mutate(Route route) {
        for(int i = 1; i < route.getCities().length; i++) {
            if(rand.nextDouble() < MUTATION_RATE) {
                int y = (int)(rand.nextDouble()*(route.getCities().length-1)+1);
                City temp = route.getCities()[i];
                route.getCities()[i] = route.getCities()[y];
                route.getCities()[y] = temp;
            }
        }
        return route;
    }
}

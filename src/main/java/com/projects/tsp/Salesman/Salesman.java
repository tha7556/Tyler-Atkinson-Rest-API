package com.projects.tsp.Salesman;


import com.projects.tsp.utility.City;
import com.projects.tsp.utility.Route;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/**
 * An Abstract Salesman Object, intended to be extended for different implementations of TSP
 * @author Tyler Atkinson
 */
public abstract class Salesman {
	protected City[] cities;
	protected Route bestRoute, worstRoute;
	protected double bestFitness, worstFitness;
	protected double mean, sum, sqrSum;
	/**
	 * Creates a new Salesman based on an Array of Cities, and also creates a window to visualize if show = true
	 * @param cities The Array of Cities
	 */
	public Salesman(City[] cities) {
		this.cities = cities;
		bestRoute = new Route(cities);
		worstRoute = bestRoute;
		bestFitness = bestRoute.calcFitness();
		worstFitness = bestFitness;
	}
	/**
	 * Gets the Array of Cities
	 * @return The Array of Cities
	 */
	public City[] getCities() {
		return cities;
	}

	/**
	 * Abstract method used to compute the shortest path
	 */
	public abstract void compute();
	/**
	 * Evaluates each Route and modifies the bestFitness and BestRoute variables
	 * @param route The current Route
	 */
	public void compareRoute(Route route) {
		double fitness = route.calcFitness();
		mean += route.getDistance();
		sum += route.getDistance();
		sqrSum += Math.pow(route.getDistance(),2.0);
		if(fitness > bestFitness) {
			bestFitness = fitness;
			bestRoute = route;
			updateRoute(bestRoute);
		}
		if(fitness < worstFitness) {
			worstFitness = fitness;
			worstRoute = route;
		}
	}
	public void updateRoute(Route route) {

	}

	/**
	 * Randomly shuffles an Array of cities
	 * @param array The Array of Cities to shuffle
	 * @return A shuffled version of the original array.
	 */
	public static City[] shuffleArray(City[] array, boolean dontShuffleStart) {
		ArrayList<City> list = new ArrayList<>(array.length);
		if(!dontShuffleStart) {
			Collections.addAll(list, array);
			Collections.shuffle(list);
		}
		else {
			Collections.addAll(list, Arrays.copyOfRange(array,1,array.length));
			Collections.shuffle(list);
			list.add(0,array[0]);
		}
		return list.toArray(new City[0]);
	}
	/**
	 * Randomly shuffles a Route
	 * @param r The Route to shuffle
	 * @return A shuffled version of the original route.
	 */
	public static Route shuffleRoute(Route r) {
		return new Route(shuffleArray(r.getCities(),false));
	}
	public Route getBestRoute() {
		return bestRoute;
	}
	public Route getWorstRoute() {
		return worstRoute;
	}

}

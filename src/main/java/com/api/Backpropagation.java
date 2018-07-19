package com.api;

import com.api.controllers.BackpropagationParams;
import com.projects.backpropagation.Network;

import java.util.HashMap;
import java.util.Map;

public class Backpropagation {
    private Network network;
    private final double error;
    public Backpropagation(BackpropagationParams params) {
        network = new Network(params.getInput().length, params.getExpectedOutput()[0].length, params.getNumHidden());
        network.train(params.getInput(),params.getExpectedOutput(),params.getTargetError());
        error = network.calculateErrors();
    }
    public Node[] getInputNodes() {
        Node[] result = new Node[network.getInputSize()];
        for(int i = 0; i < result.length; i++) {
            result[i] = new Node(network.getInputNodes().get(i).getName(), network.getInputNodes().get(i).getData());
        }
        return result;
    }

    public Node[] getHiddenNodes() {
        Node[] result = new Node[network.getHiddenSize()];
        for(int i = 0; i < result.length; i++) {
            result[i] = new Node(network.getHiddenNodes().get(i).getName(), network.getHiddenNodes().get(i).getData());
        }
        return result;
    }

    public Node[] getOutputNodes() {
        Node[] result = new Node[network.getOutputSize()];
        for(int i = 0; i < result.length; i++) {
            result[i] = new Node(network.getOutputNodes().get(i).getName(), network.getOutputNodes().get(i).getData());
        }
        return result;
    }

    public Map<String, Double> getWeightMap() {
        Map<String, Double> result = new HashMap<>();
        for(String key : network.getWeights().keySet()) {
            result.put(key,network.getWeights().get(key).getValue());
        }
        return result;
    }
    public double getError() {
        return error;
    }
    public void run(double[] inputs) {
        network.enterInputs(inputs);
        network.runNetwork();
    }
}

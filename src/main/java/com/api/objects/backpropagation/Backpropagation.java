package com.api.objects.backpropagation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.projects.backpropagation.Network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@JsonDeserialize(using = Backpropagation.Deserializer.class)
public class Backpropagation {
    private Network network;
    private double error;
    public Backpropagation(BackpropagationParams params) {
        network = new Network(params.getInput()[0].length, params.getExpectedOutput()[0].length, params.getNumHidden());
        network.train(params.getInput(),params.getExpectedOutput(),params.getTargetError());
        error = network.calculateErrors();
    }
    public Backpropagation(Network network) {
        this.network = network;
        this.error = network.calculateErrors();
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
        return network.getWeights();
    }
    public double getError() {
        return error;
    }
    public double[] run() {
        ArrayList<com.projects.backpropagation.Node> outputNodes = network.runNetwork();
        double[] result = new double[outputNodes.size()];
        for(int i = 0; i < outputNodes.size(); i++) {
            result[i] = outputNodes.get(i).getData();
        }
        return result;
    }
    public static class Deserializer extends JsonDeserializer<Backpropagation> {
        @Override
        public Backpropagation deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            ObjectCodec oc = jp.getCodec();
            JsonNode node = oc.readTree(jp);

            ObjectMapper mapper = new ObjectMapper();
            Node[] inputNodes = mapper.convertValue(node.get("inputNodes"),Node[].class);
            Node[] hiddenNodes = mapper.convertValue(node.get("hiddenNodes"), Node[].class);
            Node[] outputNodes = mapper.convertValue(node.get("outputNodes"), Node[].class);
            HashMap<String, Double> weightMap = mapper.convertValue(node.get("weightMap"),HashMap.class);
            return new Backpropagation(new Network(inputNodes, hiddenNodes, outputNodes, weightMap));
        }
    }
}

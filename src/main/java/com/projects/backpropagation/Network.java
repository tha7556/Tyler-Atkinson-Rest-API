package com.projects.backpropagation;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Network implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Node> inputNodes, hiddenNodes, outputNodes;
	private Map<String,Double> weightMap;
	Node biasNode;
	private static final double learningRate = .5;
	
	public Network(int inputs, int outputs,int numHidden) {
		this.inputNodes = new ArrayList<>();
		hiddenNodes = new ArrayList<>();
		this.outputNodes = new ArrayList<>();
		weightMap = new HashMap<>();
		for(int i = 0; i < inputs; i++) {
			inputNodes.add(new Node("Input_"+(i+1)));
		}
		for(int i = 0; i < outputs; i++) {
			outputNodes.add(new Node("Output"+(i+1)));
		}
		
		for(int i = 0; i < numHidden; i++) {
			hiddenNodes.add(new Node("Hidden_"+(i+1)));
		}
		biasNode = new Node("Bias",1.0);
		setUpNetwork();
	}
	public Network(com.api.objects.backpropagation.Node[] inputNodes, com.api.objects.backpropagation.Node[] hiddenNodes, com.api.objects.backpropagation.Node[] outputNodes, HashMap<String, Double> weightMap) {
		this.inputNodes = new ArrayList<>(inputNodes.length);
		for(int i = 0; i < inputNodes.length; i++) {
			this.inputNodes.add(new Node(inputNodes[i].getName(), inputNodes[i].getData()));
		}
		this.hiddenNodes = new ArrayList<>(hiddenNodes.length);
		for(int i = 0; i < hiddenNodes.length; i++) {
			this.hiddenNodes.add(new Node(hiddenNodes[i].getName(), hiddenNodes[i].getData()));
		}
		this.outputNodes = new ArrayList<>(outputNodes.length);
		for(int i = 0; i < outputNodes.length; i++) {
			this.outputNodes.add(new Node(outputNodes[i].getName(), outputNodes[i].getData()));
		}
		this.weightMap = weightMap;
		biasNode = new Node("Bias",1.0);
	}
	public static double getRandom() {
		Random r = new Random();
		double result = (r.nextInt(8)+1.0)/10; //number between .1-.9
		if(r.nextBoolean())
			result *= -1.0; //Randomly makes the number +/-
		return result;		
	}
	public ArrayList<Node> getInputNodes() {
		return inputNodes;
	}
	public ArrayList<Node> getHiddenNodes() {
		return hiddenNodes;
	}
	public ArrayList<Node> getOutputNodes() {
		return outputNodes;
	}
	public Map<String,Double> getWeights() {
		return weightMap;
	}
	/**
	 * Randomly adds the weights to the network and sets up biases
	 */
	public void setUpNetwork() {
		for(Node inNode : inputNodes) {
			for(Node hiddenNode : hiddenNodes) {
				double weight = getRandom();
				weightMap.put(hiddenNode.getName()+biasNode.getName(), weight);
				//System.out.println(hiddenNode.getName()+"->Bias: "+hiddenNode.getBiasWeight().getValue());
				weight = getRandom();
				weightMap.put(inNode.getName()+hiddenNode.getName(), weight);
				//System.out.println(inNode.getName()+"->"+hiddenNode.getName()+": "+weightMap.get(inNode.getName()+"+"+hiddenNode.getName()).getValue());
			}
		}
		for(Node hiddenNode : hiddenNodes) {
			for(Node outNode : outputNodes) {
				double weight = getRandom();
				weightMap.put(outNode.getName()+biasNode.getName(),weight);
				//System.out.println(outNode.getName()+"->Bias: "+outNode.getBiasWeight().getValue());
				weight = getRandom();
				weightMap.put(hiddenNode.getName()+outNode.getName(), weight);
				//System.out.println(hiddenNode.getName()+"->"+outNode.getName()+": "+weightMap.get(hiddenNode.getName()+"+"+outNode.getName()).getValue());

			}
		}
	}
	public void train(double[][] trainingSetInputs, double[][] trainingSetOutputs, double targetError) {
		double error = 999.9;
		int j = 0;
		//com.projects.backpropagation.Network.write("Epoch,Error,Input1->hidden1 weight","E:\\\\ComputerScience\\\\Workspace\\\\Ai Project1\\\\\\\\ShapeError.csv",false);
		while(error >= targetError) {
			j++;
			Random random = new Random();
			int i = random.nextInt(trainingSetInputs.length);
			double[] inputs = trainingSetInputs[i];
			double[] outputs = trainingSetOutputs[i];
			enterInputs(inputs);
			for(int x = 0; x < outputs.length; x++) {
				outputNodes.get(x).setTarget(outputs[x]);
			}
			
			runNetwork();
			error = calculateErrors();
			DecimalFormat decimalFormat = new DecimalFormat("#.000000000000000000000");
			//if(j % 50 == 0 || j==0)
				//com.projects.backpropagation.Network.write(j+","+error+","+weightMap.get(inputNodes.get(0).getName()+"+"+hiddenNodes.get(0).getName()).getValue()+","+weightMap.get(hiddenNodes.get(0).getName()+"+"+outputNodes.get(0).getName()).getValue(),"E:\\\\ComputerScience\\\\Workspace\\\\Ai Project1\\\\ShapeError.csv",true);
			//System.out.println(j+","+decimalFormat.format(error));
			calculateNewWeights();
			
		}
	}
	public void enterInputs(double[] inputs) {
		for(int i = 0; i < inputs.length; i++) {
			inputNodes.get(i).setData(inputs[i]);
		}
	}
	/**
	 * Calculates the value of the output Nodes at the end of the network
	 * @return An ArrayList of the output Nodes
	 */
	public ArrayList<Node> runNetwork() {
		for(Node hidden : hiddenNodes) {
			double data = weightMap.get(hidden.getName()+biasNode.getName()) * biasNode.getData();
			for(Node input : inputNodes) {
				data += input.getData() * weightMap.get(input.getName()+hidden.getName());
			}
			data = 1/(1+Math.exp(-data));
			hidden.setData(data);
		}
		for(Node output : outputNodes ) {
			double data = weightMap.get(output.getName()+biasNode.getName()) * biasNode.getData();
			for(Node hidden : hiddenNodes) {
				data += hidden.getData() * weightMap.get(hidden.getName()+output.getName());
			}
			data = Network.sigmoid(data);
			output.setData(data);
		}		
		return outputNodes;
	}
	/**
	 * Assigns error signals to each neuron
	 * @return The RMSE for the network
	 */
	public double calculateErrors() {
		double totalError = 0.0;
		for(Node output : outputNodes) {
			double error = (output.getTarget() - output.getData()) * Network.sigmoidDerivative(output.getData()); 
			output.setError(error);
			totalError += Math.pow(error,2);
		}
		for(Node hidden : hiddenNodes) {
			double error = 0.0;
			for(Node output : outputNodes) {
				error += output.getError() * weightMap.get(hidden.getName()+output.getName());
			}
			error *= Network.sigmoidDerivative(hidden.getData());
			hidden.setError(error);
		}
		totalError = totalError / (double)outputNodes.size();
		return Math.sqrt(totalError);
	}
	public void calculateNewWeights() {
		for(Node output : outputNodes) {
			double weight = weightMap.get(output.getName()+biasNode.getName());
			double newWeight = weight + (learningRate * output.getError() * biasNode.getData());
			weight = newWeight;
			weightMap.put(output.getName()+biasNode.getName(),weight);

			for(Node hidden : hiddenNodes) {
				weight = weightMap.get(hidden.getName()+output.getName());
				newWeight = weight + (learningRate * output.getError() * hidden.getData());
				weight = newWeight;
				weightMap.put(hidden.getName()+output.getName(),weight);
			}
		}
		for(Node hidden : hiddenNodes) {
			double weight = weightMap.get(hidden.getName()+biasNode.getName());
			double newWeight = weight + (learningRate * hidden.getError() * biasNode.getData());
			weight = newWeight;
			weightMap.put(hidden.getName()+biasNode.getName(),weight);
			for(Node input : inputNodes) {
				weight = weightMap.get(input.getName()+hidden.getName());
				newWeight = weight + (learningRate * hidden.getError() * input.getData());
				weight = newWeight;
				weightMap.put(input.getName()+hidden.getName(),weight);
			}
		}
	}
	public Integer getInputSize() {
		return inputNodes.size();
	}
	public Integer getOutputSize() {
		return outputNodes.size();
	}
	public Integer getHiddenSize() {
		return hiddenNodes.size();
	}
	public static double sigmoid(double val) {
		return 1.0 / (1 + Math.exp(-val));
	}
	public static double sigmoidDerivative(double val) {
		return val*(1.0-val);
	}

}

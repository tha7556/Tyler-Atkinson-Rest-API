package com.projects.backpropagation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
	private final double learningRate = .5;
	
	public Network(int inputs, int outputs,int numHidden) {
		this.inputNodes = new ArrayList<Node>();
		hiddenNodes = new ArrayList<Node>();
		this.outputNodes = new ArrayList<Node>();
		weightMap = new HashMap<String,Double>();
		for(int i = 0; i < inputs; i++) {
			inputNodes.add(new Node("Input_"+(i+1)));
		}
		for(int i = 0; i < outputs; i++) {
			outputNodes.add(new Node("Output"+(i+1)));
		}
		
		for(int i = 0; i < numHidden; i++) {
			hiddenNodes.add(new Node("Hidden_"+(i+1)));
		}
		biasNode = new Node("+Bias",1.0);
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
		this.weightMap = weightMap; //TODO: delete Weight, have bias weights in weightMap, do something with bias nodes
		biasNode = new Node("+Bias",1.0);
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
	public Integer train(double[][] trainingSetInputs,double[][] trainingSetOutputs, double targetError) {
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
		return j;
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
				data += input.getData() * (Double)weightMap.get(input.getName()+hidden.getName());
			}
			data = 1/(1+Math.exp(-data));
			hidden.setData(data);
		}
		for(Node output : outputNodes ) {
			double data = weightMap.get(output.getName()+biasNode.getName()) * biasNode.getData();
			for(Node hidden : hiddenNodes) {
				data += hidden.getData() * (Double)weightMap.get(hidden.getName()+output.getName());
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
	private void writeObject(ObjectOutputStream o)
	{  
		    
		try 
		{
			o.defaultWriteObject();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	private void readObject(ObjectInputStream o) {    
		try {
			o.defaultReadObject();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void saveNetwork(String fileName) {
		try
	    {
	       FileOutputStream fileOut = new FileOutputStream(fileName);
	       ObjectOutputStream out = new ObjectOutputStream(fileOut);
	       out.writeObject(this);
	       out.close();
	       fileOut.close();
	    } 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void loadNetwork(String fileName) {
		try
	    {
	        FileInputStream fileIn = new FileInputStream(fileName);
	        ObjectInputStream in = new ObjectInputStream(fileIn);
			Network network = (Network) in.readObject();

	        in.close();
	        fileIn.close();
	        this.inputNodes = network.getInputNodes();
	        this.hiddenNodes = network.getHiddenNodes();
	        this.outputNodes = network.getOutputNodes();
	        this.weightMap = network.getWeights();
	    } 
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
	public static void write(String string, String fileName, boolean append) {
		File file = new File(fileName);
		FileWriter fWriter;
		try {
			fWriter = new FileWriter (file,append);
			PrintWriter pWriter = new PrintWriter(fWriter);
			pWriter.println(string);
			pWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	public static double sigmoid(double val) {
		return 1.0 / (1 + Math.exp(-val));
	}
	public static double sigmoidDerivative(double val) {
		return val*(1.0-val);
	}
	public static void main(String[] args) {
		double[][] trainingSetInputs = {{.1,.1},{.1,.9},{.9,.1},{.9,.9}};
		double[][] trainingSetOutputs = {{.1},{.9},{.9},{.1}}; //XOR
		//double[][] trainingSetOutputs = {{.1},{.1},{.1},{.9}}; //AND
		//double[][] trainingSetOutputs = {{.1},{.9},{.9},{.9}}; //OR
		//double[][] trainingSetOutputs = {{.1,.1,.1},{.9,.1,.9},{.9,.1,.9},{.1,.9,.9}}; //All 3
	
		double targetError = .000000000000001;
		double av = 0.0;
		Network n = new Network(2,1,2);
		n.train(trainingSetInputs, trainingSetOutputs, targetError);
		//n.saveNetwork("E:\\ComputerScience\\Github\\Ai-Backpropagation\\Data\\ANDNetwork.ntwrk");
		//n.loadNetwork("E:\\ComputerScience\\Github\\Ai-Backpropagation\\Data\\ANDNetwork.ntwrk");
		
		
		//Testing
		
		DecimalFormat decimalFormat = new DecimalFormat("#.0");
		if(n.getOutputNodes().size() == 3) {
			System.out.println("\n\n X  Y|  XOR AND OR");
			for(double[] arr : trainingSetInputs) {
				n.enterInputs(arr);
				n.runNetwork();
				System.out.printf("%s %s|  %s %s %s\n",
						decimalFormat.format(arr[0]),decimalFormat.format(arr[1]),decimalFormat.format(n.getOutputNodes().get(0).getData()),
						decimalFormat.format(n.getOutputNodes().get(1).getData()),decimalFormat.format(n.getOutputNodes().get(2).getData()));
			}
		}
		else if(n.getOutputNodes().size() == 1) {
			System.out.println("\n\n X  Y|  Output");
			for(double[] arr : trainingSetInputs) {
				n.enterInputs(arr);
				n.runNetwork();
				System.out.printf("%s %s|  %s\n",
						decimalFormat.format(arr[0]),decimalFormat.format(arr[1]),decimalFormat.format(n.getOutputNodes().get(0).getData()));
			}
		}
		
	
	}

}

package com.api.controllers;

public class BackpropagationParams {
    private double[][] input, expectedOutput;
    private int numHidden;
    private double targetError;
    public BackpropagationParams() {

    }
    public BackpropagationParams(double[][] input, int numHidden, double[][] expectedOutput, double targetError) {
        this.input = input;
        this.expectedOutput = expectedOutput;
        this.numHidden = numHidden;
        this.targetError = targetError;
    }

    public double[][] getInput() {
        return input;
    }

    public int getNumHidden() {
        return numHidden;
    }

    public double[][] getExpectedOutput() {
        return expectedOutput;
    }

    public double getTargetError() {
        return targetError;
    }

    public void setInput(double[][] input) {
        this.input = input;
    }

    public void setNumHidden(int numHidden) {
        this.numHidden = numHidden;
    }

    public void setExpectedOutput(double[][] expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public void setTargetError(double targetError) {
        this.targetError = targetError;
    }
}

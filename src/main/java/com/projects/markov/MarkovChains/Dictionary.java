package com.projects.markov.MarkovChains;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
	private ArrayList<String> words;
	public Dictionary(String fileName) {
		words = new ArrayList<>();
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String s = scanner.nextLine();
				s = s.trim();
				if(!s.contains("-") && !s.contains("'") && s.length() > 1)
					words.add(s.toLowerCase());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<String> getWords() {
		return words;
	}
	public String toString() {
		StringBuilder string = new StringBuilder();
		for(int i = 0; i < words.size(); i++) {
			if(i+1 % 12 == 0)
				string.append(words.get(i)).append("\n");
			else
				string.append(words.get(i)).append("  ");
		}
		return string.toString();
	}
	public Boolean contains(String word) {
		return words.contains(word.toLowerCase());
	}
}

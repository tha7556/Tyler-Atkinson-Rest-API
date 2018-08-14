package com.projects.markov.TweetGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TweetChain {
	private final static String NIL = "\0";
	private static Random rand = new Random();
	private Map<String, Integer> table;
	private TweetDictionary dictionary;
	private ArrayList<String> knownWords;
	private final static String[] startSymbols = new String[]{"#","\"","\'","@","$","(","*","%"};
	private final static String[] endSymbols = new String[]{",",".","\"","\'","!",")","*","?"};
	private int max, min;
	public TweetChain(TweetDictionary dict) {
		this.dictionary = dict;
		this.knownWords = new ArrayList<>();
		knownWords.add(NIL);
		this.table = new HashMap<>();
		max = -1;
		min = Integer.MAX_VALUE;
		putAllTweetsInTable();
	}
	public String writeTweet() {
		ArrayList<String> tweet = new ArrayList<>();
		tweet.add(NIL);
		int size = 0;
		while((size <= 1 ||  !tweet.get(tweet.size()-1).equals(NIL)) && size < 280) {
			ArrayList<String> words = new ArrayList<>();
			ArrayList<Integer> values = new ArrayList<>();
			String lastWord = tweet.get(tweet.size()-1);
			for(String word : knownWords) {
				int count = getCountForTransition(lastWord+word);
				if(count > 0) {
					words.add(word);
					values.add(count);
				}
			}
			int target = rand.nextInt((max - min) + 1) + min; //Random num between min and max
			int index = 0, value = 0;
			for(; value < target; index++) {
				if(index >= words.size())
					index = 0;
				value += values.get(index);
			}
			index--;
			if(index < 0)
				index = words.size() - 1;
			tweet.add(words.get(index));
			size += words.get(index).length();
		}
		
		return TweetChain.formatResult(tweet);
	}
	public void putTweetInTable(String tweet) {
		ArrayList<String> words = dictionary.getWordsFromTweet(tweet);
		for(int i = 0; i < words.size()+1; i++) {
			if(i < words.size() && !knownWords.contains(words.get(i))) { //new word
				knownWords.add(words.get(i));
			}
			String transition;
			if(i == 0) { //first word
				transition = NIL+words.get(i);
			}
			else if(i == words.size()) { //last word
				transition = words.get(i-1)+NIL;
			}
			else { //middle words
				transition = words.get(i-1)+words.get(i);
			}
			if(getCountForTransition(transition) == 0) { //not in table
				table.put(transition, 1);
				if(1 > max)
					max = 1;
				if(1 < min)
					min = 1;
			}
			else { //already in table
				int num = table.get(transition);
				table.put(transition, num+1);
				if((num+1) > max)
					max = num+1;
				if((num+1) < min)
					min = num+1;
			}
		}
		
	}
	public void putAllTweetsInTable() {
		for(String tweet : dictionary.getTweets()) {
			putTweetInTable(tweet.toLowerCase());
		}
	}
	public int getCountForTransition(String transition) {
		if(!table.containsKey(transition)) //transition not in table yet
			return 0;
		else {
			return table.get(transition);
		}
	}
	public Map<String,Integer> getTable() {
		return table;
	}
	public ArrayList<String> getKnownWords() {
		return knownWords;
	}
	public static String formatResult(ArrayList<String> words) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < words.size(); i++) {
			if(words.get(i).equals("i"))
				words.set(i,"I");
			else if(words.get(i).contains("i\'"))
				words.set(i, "I" + words.get(i).substring(1));
			else if(words.get(i).equals("america"))
				words.set(i, "America");
			
			if(isIn(words.get(i),startSymbols)) {
				builder.append(words.get(i));
			}
			else if(i < words.size()-1 && isIn(words.get(i+1),endSymbols)) {
				builder.append(words.get(i));
			}
			else {
				builder.append(words.get(i)).append(" ");
			}
		}
		String result = builder.toString().trim();
		if(result.length() > 0) {
			result = Character.toUpperCase(result.charAt(0))+result.substring(1);
		}
		return result;
	}
	public static boolean isIn(String string, String[] arr) {
		for(String s : arr) 
			if(string.equals(s))
				return true;
			
		return false;
	}
	
}

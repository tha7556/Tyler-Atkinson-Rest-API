package com.api.objects.markov;

import com.projects.markov.TweetGenerator.TweetChain;
import com.projects.markov.TweetGenerator.TweetDictionary;

public class Markov {
    private final String user;
    private TweetDictionary dictionary;
    private TweetChain chain;
    public Markov(String user) {
        this.user = user;
        dictionary = new TweetDictionary(user);
        chain = new TweetChain(dictionary);
        System.out.println("Initialized");
    }
    public String getUser() {
        return user;
    }
    public Tweet[] getTweets(int number) {
        Tweet[] result = new Tweet[number];
        for(int i = 0; i < number; i++) {
            result[i] = new Tweet(user,chain.writeTweet());
            System.out.println(result[i].getTweet());
        }
        return result;
    }
}

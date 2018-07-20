package com.api.objects.markov;

import com.projects.markov.TweetGenerator.TweetChain;
import com.projects.markov.TweetGenerator.TweetDictionary;
import twitter4j.TwitterException;

public class Markov {
    private final String user;
    private TweetDictionary dictionary;
    private TweetChain chain;
    public Markov(String user) throws TwitterException {
        this.user = user;
        dictionary = new TweetDictionary(user);
        chain = new TweetChain(dictionary);
    }
    public String getUser() {
        return user;
    }
    public Tweet[] getTweets(int number) {
        Tweet[] result = new Tweet[number];
        for(int i = 0; i < number; i++) {
            result[i] = new Tweet(user,chain.writeTweet());
        }
        return result;
    }
}

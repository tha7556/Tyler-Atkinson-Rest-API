package com.api;

public class Tweet {
    private final String user, tweet;

    public Tweet(String user, String tweet) {
        this.user = user;
        this.tweet = tweet;
    }
    public String getUser() {
        return user;
    }
    public String getTweet() {
        return tweet;
    }
}

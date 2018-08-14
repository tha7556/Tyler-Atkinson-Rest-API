package com.api.objects.markov;

public class Tweet {
    private final String user, tweet, name, imageUrl;

    public Tweet(String user, String tweet, String name, String imageUrl) {
        this.user = user;
        this.tweet = tweet;
        this.name = name;
        this.imageUrl = imageUrl;
    }
    public String getUser() {
        return user;
    }
    public String getTweet() {
        return tweet;
    }
    public String getName() {
        return name;
    }
    public String getImageUrl() {
        return imageUrl;
    }
}

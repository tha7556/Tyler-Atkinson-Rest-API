package com.api.controllers;

import com.api.objects.markov.Markov;
import com.api.objects.markov.Tweet;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MarkovController {

    @RequestMapping(value = {"markov/{user}"}, method = GET)
    public Tweet getTweet(@PathVariable String user) {
        return new Markov(user).getTweets(1)[0];
    }
    @RequestMapping(value = {"markov/{user}/{quantity}"}, method = GET)
    public Tweet[] getTweets(@PathVariable String user, @PathVariable int quantity) {
        return new Markov(user).getTweets(quantity);
    }

}

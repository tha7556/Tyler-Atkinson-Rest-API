package com.api.controllers;

import com.api.objects.markov.Markov;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twitter4j.TwitterException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MarkovController {

    @RequestMapping(value = {"markov/{user}"}, method = GET)
    public ResponseEntity<?> getTweet(@PathVariable String user) {
        try {
            return new ResponseEntity<>(new Markov(user).getTweets(1)[0], new HttpHeaders(), HttpStatus.OK);
        }
        catch(TwitterException exception) {
            return new ResponseEntity<>("Something went wrong while gathering tweets, likely an incorrect twitter handle", HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = {"markov/{user}/{quantity}"}, method = GET)
    public ResponseEntity<?> getTweets(@PathVariable String user, @PathVariable long quantity) {
        try {
            if (quantity < 1000 && quantity > -1)
                return new ResponseEntity<>(new Markov(user).getTweets((int)quantity), new HttpHeaders(), HttpStatus.OK);
            else
                return new ResponseEntity<>("Quantity must be within [0,1000)",HttpStatus.BAD_REQUEST);
        }
        catch(TwitterException exception) {
            return new ResponseEntity<>("Something went wrong while gathering tweets, likely an incorrect twitter handle",HttpStatus.NOT_FOUND);
        }


    }

}

import { Component, OnInit, Input } from '@angular/core';
import { MarkovService } from '../markov.service';
import { Tweet } from '../tweet';

@Component({
  selector: 'app-markov',
  templateUrl: './markov.component.html',
  styleUrls: ['./markov.component.css']
})
export class MarkovComponent implements OnInit {
  constructor(private markovService: MarkovService) { }
  public user: string;
  public numberOfResults: number;
  public running = false;
  tweets: Tweet[];
  ngOnInit() {
  }
  submit() {
    if (typeof this.numberOfResults !== 'number' || this.numberOfResults < 1 || this.numberOfResults > 20) {
      throw new Error('Number of results must be between 1 and 20');
    }
    if (typeof this.user === 'undefined' || this.user.length < 1) {
      throw new Error('Twitter Handle cannot be empty');
    }
    this.tweets = [];
    this.running = true;
    this.markovService.getTweets(this.user, this.numberOfResults).subscribe(tweets => {this.running = false; this.tweets = tweets; },
      err => {
      this.running = false;
      throw new Error(err.error); });
  }

}

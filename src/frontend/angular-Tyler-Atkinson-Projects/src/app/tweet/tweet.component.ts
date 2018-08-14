import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css']
})
export class TweetComponent implements OnInit {
  @Input() text: string;
  @Input() user: string;
  @Input() name: string;
  @Input() imageUrl: string;
  constructor() { }

  ngOnInit() {
  }

}

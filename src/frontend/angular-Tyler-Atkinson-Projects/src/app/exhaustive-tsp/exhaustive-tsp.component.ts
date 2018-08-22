import { Component, OnInit } from '@angular/core';
import { PageManagerService } from '../page-manager.service';

@Component({
  selector: 'app-exhaustive-tsp',
  templateUrl: './exhaustive-tsp.component.html',
  styleUrls: ['./exhaustive-tsp.component.css']
})
export class ExhaustiveTspComponent implements OnInit {

  constructor(public pageManager: PageManagerService) { }

  ngOnInit() {
  }

}

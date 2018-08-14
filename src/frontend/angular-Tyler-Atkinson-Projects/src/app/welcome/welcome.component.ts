import { Component, OnInit } from '@angular/core';
import { PageManagerService } from '../page-manager.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(public pageManager: PageManagerService) { }

  ngOnInit() {
  }

}

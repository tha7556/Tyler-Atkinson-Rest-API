import { Component, ErrorHandler, OnInit, ViewChild, ElementRef } from '@angular/core';
import { PageManagerService } from './page-manager.service';
import { WebService } from './web.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements ErrorHandler, OnInit {
  private message: string;
  private index: number;
  @ViewChild('welcomeButton') welcome: ElementRef;
  @ViewChild('aboutButton') about: ElementRef;
  @ViewChild('contactButton') contact: ElementRef;
  @ViewChild('projectButton') project: ElementRef;
  private navElements: ElementRef[];
  constructor(public pageManager: PageManagerService, private web: WebService) {}
  ngOnInit() {
    this.navElements = [this.about, this.contact, this.project];
    this.pageManager.setElements(this.navElements);
    this.pageManager.setIndex(3);
    this.web.checkStatus().subscribe(result => console.log(result), err => console.log(err));
  }
  handleError(error: any) {
    if (error.status === 0) {
      this.message = 'Error: Unable to connect to the server';
    } else if (typeof error.status !== 'undefined') {
      this.message = 'Error Code: ' + error.status + '\n';
      if (error.error.error) {
        this.message += error.error.error;
      } else {
        this.message += error.error;
      }
    } else {
      this.message = error + '';
    }
    console.log(this.message);
    this.showMessage();
  }
  showMessage() {
    window.alert(this.message);
  }
}

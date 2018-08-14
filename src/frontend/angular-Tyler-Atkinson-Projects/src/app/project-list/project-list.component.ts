import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
  index: number;
  constructor() { }

  ngOnInit() {
    this.index = -1;
  }
  changeIndex(index: number) {
    this.index = index;
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
  }

}

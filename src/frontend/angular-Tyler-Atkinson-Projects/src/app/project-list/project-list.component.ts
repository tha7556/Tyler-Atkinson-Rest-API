import { Component, OnInit } from '@angular/core';
import { PageManagerService } from '../page-manager.service';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
  constructor(public pageManager: PageManagerService) { }

  ngOnInit() {
    this.pageManager.projectIndex = -1;
  }

}

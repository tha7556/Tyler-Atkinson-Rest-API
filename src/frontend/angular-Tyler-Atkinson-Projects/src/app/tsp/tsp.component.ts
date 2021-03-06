import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { City } from '../city';
import { SalesmanService } from '../salesman.service';
import { Line } from '../line';

@Component({
  selector: 'app-tsp',
  templateUrl: './tsp.component.html',
  styleUrls: ['./tsp.component.css']
})
export class TSPComponent implements OnInit {
  public cities: City[];
  private selected: City;
  private offsetX: number;
  private offsetY: number;
  private startTime: number;
  public running = false;
  private preventScroll = false;

  public lines: Line[];
  @ViewChild('svg') private box: ElementRef;
  constructor(private salesmanService: SalesmanService) { }

  ngOnInit() {
    this.cities = [];
    this.lines = [];
    this.cities.push({x: 138, y: 50, name: 'A'});
    this.cities.push({x: .60 * window.innerWidth, y: 125, name: 'B'});
    this.cities.push({x: .50 * window.innerWidth, y: 300, name: 'C'});
    this.cities.push({x: .25 * window.innerWidth, y: 170, name: 'D'});
  }
  mouseDown(event: MouseEvent | TouchEvent, item: City) {
    if (event instanceof MouseEvent) {
      if (!this.running) {
        this.selected = item;
        this.offsetX = event.clientX;
        this.offsetY = event.clientY;
        this.lines = [];
      }
    } else {
      if (!this.running && event.touches.length === 1) {
        this.selected = item;
        this.offsetX = event.touches[0].clientX;
        this.offsetY = event.touches[0].clientY;
        this.lines = [];
        this.preventScroll = true;
      }
    }
  }
  mouseUp() {
    this.selected = null;
    this.preventScroll = false;
  }
  mouseMove(event: MouseEvent | TouchEvent) {
    if (event instanceof MouseEvent) {
      if (this.selected != null) {
        this.selected.x += (event.clientX - this.offsetX);
        this.selected.y += (event.clientY - this.offsetY);
        this.offsetX = event.clientX;
        this.offsetY = event.clientY;
      }
    } else {
      if (this.selected != null && event.touches.length === 1) {
        this.selected.x += (event.touches[0].clientX - this.offsetX);
        this.selected.y += (event.touches[0].clientY - this.offsetY);
        this.offsetX = event.touches[0].clientX;
        this.offsetY = event.touches[0].clientY;
        if (this.preventScroll) {
          event.preventDefault();
        }
      }
    }
  }
  addCity() {
    if (!this.running) {
      this.lines = [];
      if (this.cities.length > 25) {
        throw new Error('Cannot add any more cities');
      }
      const letter = String.fromCharCode(this.cities[this.cities.length - 1].name.charCodeAt(0) + 1);
      this.cities.push({x: Math.random() * this.box.nativeElement.width.baseVal.value,
        y: Math.random() * this.box.nativeElement.height.baseVal.value, name: letter});
    }
  }
  removeCity() {
    if (!this.running) {
      this.lines = [];
      if (this.cities.length > 4) {
        this.cities.pop();
      } else {
        throw new Error('Cannot remove any more cities');
      }
  }
  }
  genetic() {
    if (this.running) {
      return;
    }
    this.lines = [];
    if (this.cities.length < 2) {
      throw new Error('There should be at least 2 cities');
    }
    this.running = true;
    this.salesmanService.runGeneticSalesman(this.cities).subscribe(result => this.displayLines(result),
    err => {
      this.running = false;
      throw new Error('Unable to connect to server');
    } );
  }
  annealing() {
    if (this.running) {
      return;
    }
    this.lines = [];
    this.running = true;
    this.salesmanService.runAnnealingSalesman(this.cities).subscribe(result => this.displayLines(result),
    err => {
      this.running = false;
      throw new Error('Unable to connect to server');
    } );
  }
  displayLines(route: City[]) {
    this.lines = [];
    for (let i = 0; i < route.length - 1; i++) {
      this.lines.push({x1: route[i].x, y1: route[i].y, x2: route[i + 1].x, y2: route[i + 1].y});
    }
    if (route.length > 2) {
      this.lines.push({x1: route[0].x, y1: route[0].y, x2: route[route.length - 1].x, y2: route[route.length - 1].y});
    }
    this.running = false;
  }

}
